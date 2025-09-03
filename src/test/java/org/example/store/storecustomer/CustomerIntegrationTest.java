package org.example.store.storecustomer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.persistence.EntityManager;
import lombok.SneakyThrows;
import org.example.store.storecustomer.container.TestContainerConfig;
import org.example.store.storecustomer.dto.api.AllCustomerResponse;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerRequest;
import org.example.store.storecustomer.entity.Customer;
import org.example.store.storecustomer.repository.CustomerRepository;
import org.instancio.Instancio;
import org.instancio.InstancioApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import(TestContainerConfig.class)
public class CustomerIntegrationTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EntityManager entityManager;

    @Test
    public void save() throws Exception {
        Customer customer = Instancio.of(Customer.class).ignore(field(Customer::getId)).create();
        String content = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(customer.getName()))
                .andExpect(jsonPath("$.balance").value(customer.getBalance()));

        assertThat(customerRepository.save(customer)).isNotNull();
    }

    @Test
    @SneakyThrows
    public void update() throws Exception {
        Customer customer = Instancio.of(Customer.class)
                .ignore(field(Customer::getId))
                .set(field(Customer::getName), "Artem")
                .set(field(Customer::getBalance), new BigDecimal("1000.00"))
                .create();
        Customer save = customerRepository.save(customer);
        UpdateCustomerRequest updateCustomerRequest = Instancio.of(UpdateCustomerRequest.class)
                .set(field(UpdateCustomerRequest::getId),save.getId())
                .set(field(UpdateCustomerRequest::getName), "Artem")
                .set(field(UpdateCustomerRequest::getBalance), new BigDecimal("2000.00"))
                .create();
        String content = objectMapper.writeValueAsString(updateCustomerRequest);

        mockMvc.perform(put("/api/v1/customers/{id}",save.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Artem"))
                .andExpect(jsonPath("$.balance").value(2000.00));

        Customer updatedCustomer = customerRepository.findById(save.getId()).orElseThrow();
        assertThat(updatedCustomer.getName()).isEqualTo(updateCustomerRequest.getName());
        assertThat(updatedCustomer.getBalance()).isEqualTo(updateCustomerRequest.getBalance());
    }

    @Test
    public void deleteById() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/api/v1/customers/{id}", id)).andExpect(status().isNoContent());

        assertThat(customerRepository.findById(id)).isNotPresent();
    }

    @Test
    @SneakyThrows
    public void findAll() throws Exception {
        Customer customer1 = customerRepository.save(
                Instancio.of(Customer.class).ignore(field(Customer::getId)).create()
        );
        Customer customer2 = customerRepository.save(
                Instancio.of(Customer.class).ignore(field(Customer::getId)).create()
        );
        Customer customer3 = customerRepository.save(
                Instancio.of(Customer.class).ignore(field(Customer::getId)).create()
        );


        mockMvc.perform(get("/api/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers.length()").value(4));

    }
}
