package org.example.store.storecustomer.repository;

import org.example.store.storecustomer.entity.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.liquibase.enabled=false")
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
    }
    @Test
    void saveCustomer(){
        Customer customer = Instancio.of(Customer.class).ignore(field(Customer::getId)).create();
        Customer save = customerRepository.save(customer);
        Optional<Customer> insert = customerRepository.findById(save.getId());

        assertThat(insert.isPresent()).isTrue();
    }

}