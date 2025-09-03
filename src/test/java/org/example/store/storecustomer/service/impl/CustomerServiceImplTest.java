package org.example.store.storecustomer.service.impl;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.example.store.storecustomer.container.TestContainerConfig;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerRequestDto;
import org.example.store.storecustomer.dto.service.UpdateCustomerRequestDto;
import org.example.store.storecustomer.entity.Customer;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.repository.CustomerRepository;
import org.example.store.storecustomer.service.CustomerService;
import org.instancio.Instancio;
import org.instancio.InstancioCollectionsApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Test
    void save() {
        Customer customerOne = Instancio.create(Customer.class);
        SaveCustomerRequestDto saveCustomerRequestDto = Instancio.create(SaveCustomerRequestDto.class);
        SaveCustomerResponse saveCustomerResponse = Instancio.create(SaveCustomerResponse.class);
        when(customerRepository.save(customerOne)).thenReturn(customerOne);
        when(customerMapper.toEntity(saveCustomerRequestDto)).thenReturn(customerOne);
        when(customerMapper.toSaveResponseFromEntity(customerOne)).thenReturn(saveCustomerResponse);

        customerService.save(saveCustomerRequestDto);
        verify(this.customerRepository).save(customerOne);

    }
    @Test
    void findAll(){
        List<Customer> customers = Instancio.ofList(Customer.class).size(5).create();
        SaveCustomerRequestDto saveCustomerRequestDto = Instancio.create(SaveCustomerRequestDto.class);
        SaveCustomerResponse saveCustomerResponse = Instancio.create(SaveCustomerResponse.class);
        when(customerRepository.findAll()).thenReturn(customers);



        customerService.findAll();

        verify(customerRepository).findAll();

    }
    @Test
    void deleteById(){
        Long id = 1L;

        customerService.deleteById(id);

        verify(customerRepository).deleteById(id);
    }
    @Test
    void update(){
        Customer customerOne = Instancio.create(Customer.class);
        UpdateCustomerRequestDto updateCustomerRequestDto = Instancio.create(UpdateCustomerRequestDto.class);
        UpdateCustomerResponse updateCustomerResponse = Instancio.create(UpdateCustomerResponse.class);
        when(customerRepository.save(customerOne)).thenReturn(customerOne);
        when(customerMapper.toEntity(updateCustomerRequestDto)).thenReturn(customerOne);
        when(customerMapper.toUpdateResponseFromEntity(customerOne)).thenReturn(updateCustomerResponse);

        customerService.update(updateCustomerRequestDto);

        verify(customerRepository).save(customerOne);
        verify(customerMapper).toUpdateResponseFromEntity(customerOne);
        verify(customerMapper).toEntity(updateCustomerRequestDto);
    }
}