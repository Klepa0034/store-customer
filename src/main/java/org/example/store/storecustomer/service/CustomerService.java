package org.example.store.storecustomer.service;

import org.example.store.storecustomer.dto.api.AllCustomerResponse;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerRequestDto;
import org.example.store.storecustomer.dto.service.UpdateCustomerRequestDto;

import java.util.List;

/**
 * Service interface for customer management operations.
 */
public interface CustomerService {
    /**
     * Saves a new customer.
     * @param saveCustomerRequestDto DTO with customer data to save
     * @return response with saved customer data
     */
    SaveCustomerResponse save(SaveCustomerRequestDto saveCustomerRequestDto);
    /**
     * Updates an existing customer.
     * @param updateCustomerRequestDto DTO with customer data to update
     * @return response with updated customer data
     */

    UpdateCustomerResponse update(UpdateCustomerRequestDto updateCustomerRequestDto);
    /**
     * Deletes a customer by ID.
     * @param id ID of the customer to delete
     */

    void deleteById(Long id);
    /**
     * Retrieves all customers.
     * @return list of all customer responses
     */

    List<AllCustomerResponse> findAll();
}