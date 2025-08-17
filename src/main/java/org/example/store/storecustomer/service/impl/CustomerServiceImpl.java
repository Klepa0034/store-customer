package org.example.store.storecustomer.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerDto;
import org.example.store.storecustomer.entity.Customer;
import org.example.store.storecustomer.mapper.CustomerMapper;
import org.example.store.storecustomer.repository.CustomerRepository;
import org.example.store.storecustomer.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public List<SaveCustomerDto> findAllCustomer() {
        List<Customer> all = customerRepository.findAll();
        return all
                .stream()
                .map(customerMapper::toSaveCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public SaveCustomerResponse insertCustomer(SaveCustomerDto saveCustomerDto) {
        Customer eCustomer = customerMapper.toEntityCustomer(saveCustomerDto);
        Customer save = customerRepository.save(eCustomer);
        return customerMapper.toSaveCustomerResponse(save);
    }
}