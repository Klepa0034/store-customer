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
    SaveCustomerResponse save(SaveCustomerRequestDto saveCustomerRequestDto);

}