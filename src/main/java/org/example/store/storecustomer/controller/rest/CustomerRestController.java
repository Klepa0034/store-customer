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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        int a = 1;
        if (a > 1) {
            return new ResponseEntity<>(save, HttpStatus.OK);
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(save);
    }

    /**
     * Updates an existing customer.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse> update(@RequestBody UpdateCustomerRequest updateCustomerRequest) {
        UpdateCustomerRequestDto updateCustomerRequestDto = customerMapper.toUpdateRequestDtoFromRequest(updateCustomerRequest);
        UpdateCustomerResponse update = customerService.update(updateCustomerRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(update);
    }

    /**
     * Deletes a customer by ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    /**
     * Retrieves all customers.
     */
    @GetMapping
    public ListCustomerResponse findAll() {
        List<AllCustomerResponse> all = customerService.findAll();
        return new ListCustomerResponse(all);
    }

}