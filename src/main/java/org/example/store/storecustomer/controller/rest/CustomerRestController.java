package org.example.store.storecustomer.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.store.storecustomer.dto.api.SaveCustomerRequest;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerDto;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    @GetMapping
    public List<SaveCustomerDto> toSaveCustomerRequest() {
        return customerService.findAllCustomer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SaveCustomerResponse> saveCustomerRequest(@RequestBody SaveCustomerRequest saveCustomerRequest) {
        SaveCustomerDto saveCustomerDto = customerMapper.toSaveCustomerRequest(saveCustomerRequest);
        SaveCustomerResponse saveCustomerResponse = customerService.insertCustomer(saveCustomerDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(saveCustomerResponse);
    }

}
