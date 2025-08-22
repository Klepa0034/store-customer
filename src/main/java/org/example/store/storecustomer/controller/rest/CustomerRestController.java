package org.example.store.storecustomer.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.store.storecustomer.dto.api.AllCustomerResponse;
import org.example.store.storecustomer.dto.api.ListCustomerResponse;
import org.example.store.storecustomer.dto.api.SaveCustomerRequest;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.api.UpdateCustomerRequest;
import org.example.store.storecustomer.dto.api.UpdateCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerRequestDto;
import org.example.store.storecustomer.dto.service.UpdateCustomerRequestDto;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for customer management operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerRestController {
    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    /**
     * Creates a new customer.
     */
    @PostMapping
    public ResponseEntity<SaveCustomerResponse> save(@RequestBody SaveCustomerRequest saveCustomerRequest) {
        SaveCustomerRequestDto saveCustomerRequestDto = customerMapper.toSaveRequestDtoFromRequest(saveCustomerRequest);
        SaveCustomerResponse save = customerService.save(saveCustomerRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(save);
    }
}