package org.example.store.storecustomer.service.impl;

import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerDto;
import org.example.store.storecustomer.entity.Customer;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.repository.CustomerRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;


    @Test
    void findAllCustomer() {
        Customer customerOne = Instancio.create(Customer.class);
        Customer customerTwo = Instancio.create(Customer.class);
        List<Customer> customers = List.of(customerOne, customerTwo);
        SaveCustomerDto saveCustomerDto = Instancio.create(SaveCustomerDto.class);
        SaveCustomerDto saveCustomerDtoTwo = Instancio.create(SaveCustomerDto.class);
        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.toSaveCustomerDto(customerOne)).thenReturn(saveCustomerDto);
        when(customerMapper.toSaveCustomerDto(customerTwo)).thenReturn(saveCustomerDtoTwo);

        customerService.findAllCustomer();

        verify(customerRepository).findAll();

    }

    @Test
    void insertCustomer() {
        Customer customer = Instancio.create(Customer.class);
        SaveCustomerDto saveCustomerDto = Instancio.create(SaveCustomerDto.class);
        SaveCustomerResponse saveCustomerResponse = Instancio.create(SaveCustomerResponse.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerMapper.toSaveCustomerResponse(customer)).thenReturn(saveCustomerResponse);
        when(customerMapper.toEntityCustomer(saveCustomerDto)).thenReturn(customer);


        SaveCustomerResponse result = customerService.insertCustomer(saveCustomerDto);

        verify(customerMapper).toEntityCustomer(saveCustomerDto);
        verify(customerMapper).toSaveCustomerResponse(customer);
        verify(customerRepository).save(customer);
        assertThat(result).isEqualTo(saveCustomerResponse);


    }
}
