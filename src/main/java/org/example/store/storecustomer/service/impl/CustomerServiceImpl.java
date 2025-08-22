package org.example.store.storecustomer.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.store.storecustomer.dto.api.AllCustomerResponse;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerRequestDto;
import org.example.store.storecustomer.dto.service.UpdateCustomerRequestDto;
import org.example.store.storecustomer.entity.Customer;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.repository.CustomerRepository;
import org.example.store.storecustomer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of CustomerService interface.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public SaveCustomerResponse save(SaveCustomerRequestDto saveCustomerRequestDto) {
        Customer customer = customerMapper.toEntity(saveCustomerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toSaveResponseFromEntity(savedCustomer);
    }
}