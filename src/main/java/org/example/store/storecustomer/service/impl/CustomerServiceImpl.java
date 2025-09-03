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

    /**
     * Saves a new customer.
     * @param saveCustomerRequestDto DTO with customer data to save
     * @return response with saved customer data
     */
    @Override
    @Transactional
    public SaveCustomerResponse save(SaveCustomerRequestDto saveCustomerRequestDto) {
        Customer customer = customerMapper.toEntity(saveCustomerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toSaveResponseFromEntity(savedCustomer);
    }

    /**
     * Updates an existing customer.
     * @param updateCustomerRequestDto DTO with customer data to update
     * @return response with updated customer data
     */
    @Override
    @Transactional
    public UpdateCustomerResponse update(UpdateCustomerRequestDto updateCustomerRequestDto) {
        Customer customer = customerMapper.toEntity(updateCustomerRequestDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toUpdateResponseFromEntity(savedCustomer);
    }

    /**
     * Deletes a customer by ID.
     * @param id ID of the customer to delete
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    /**
     * Retrieves all customers.
     * @return list of all customer responses
     */
    @Override
    @Transactional(readOnly = true)
    public List<AllCustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toAllResponseListFromEntities(customers);
    }
}