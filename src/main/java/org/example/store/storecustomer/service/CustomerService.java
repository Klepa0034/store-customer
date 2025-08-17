package org.example.store.storecustomer.service;

import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerDto;

import java.util.List;

public interface CustomerService {
    List<SaveCustomerDto> findAllCustomer();
    SaveCustomerResponse insertCustomer(SaveCustomerDto saveCustomerDto);
}
