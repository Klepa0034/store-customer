package org.example.store.storecustomer.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.store.storecustomer.controller.ui.CustomerController;
import org.example.store.storecustomer.dto.api.AllCustomerRequest;
import org.example.store.storecustomer.dto.api.AllCustomerResponse;
import org.example.store.storecustomer.dto.api.SaveCustomerRequest;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerRequest;
import org.example.store.storecustomer.dto.api.UpdateCustomerResponse;
import org.example.store.storecustomer.dto.service.AllCustomerRequestDto;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.service.CustomerService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {
    @MockitoBean
    private CustomerService customerServiceImpl;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRestController customerRestController;


    @Test
    @SneakyThrows
    void save() {

        SaveCustomerRequest saveCustomerRequest = Instancio.create(SaveCustomerRequest.class);
        SaveCustomerResponse saveCustomerResponse = Instancio.create(SaveCustomerResponse.class);
        String content = objectMapper.writeValueAsString(saveCustomerRequest);
        when(customerServiceImpl.save(any())).thenReturn(saveCustomerResponse);

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(saveCustomerResponse.getName()))
                .andExpect(jsonPath("$.balance").value(saveCustomerResponse.getBalance()));
    }

    @Test
    @SneakyThrows
    void update() {
        UpdateCustomerRequest updateCustomerRequest = Instancio.create(UpdateCustomerRequest.class);
        UpdateCustomerResponse updateCustomerResponse = Instancio.create(UpdateCustomerResponse.class);
        String content = objectMapper.writeValueAsString(updateCustomerRequest);
        when(customerServiceImpl.update(any())).thenReturn(updateCustomerResponse);

        mockMvc.perform(put("/api/v1/customers/{id}",updateCustomerRequest.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updateCustomerResponse.getId()))
                .andExpect(jsonPath("$.name").value(updateCustomerResponse.getName()))
                .andExpect(jsonPath("$.balance").value(updateCustomerResponse.getBalance()));
    }

    @Test
    @SneakyThrows
    public void deleteById() {
        long id = 1L;

        mockMvc.perform(delete("/api/v1/customers/{id}", id))
                .andExpect(status().isNoContent());

        verify(customerServiceImpl).deleteById(id);
    }

    @Test
    @SneakyThrows
    public void findAll() {
        List<AllCustomerResponse> allCustomerResponse = Instancio.ofList(AllCustomerResponse.class).size(3).create();
        String content = objectMapper.writeValueAsString(allCustomerResponse);
        when(customerServiceImpl.findAll()).thenReturn(allCustomerResponse);

        mockMvc.perform(get("/api/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers.length()").value(3));


    }
}