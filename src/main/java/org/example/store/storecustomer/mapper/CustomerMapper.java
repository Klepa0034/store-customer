package org.example.store.storecustomer.mapper;

import org.example.store.storecustomer.dto.api.SaveCustomerRequest;
import org.example.store.storecustomer.dto.api.SaveCustomerResponse;
import org.example.store.storecustomer.dto.service.SaveCustomerDto;
import org.example.store.storecustomer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CustomerMapper {
    SaveCustomerDto toSaveCustomerDto(Customer customer);
    Customer toEntityCustomer(SaveCustomerDto customerDto);
    SaveCustomerDto toSaveCustomerRequest(SaveCustomerRequest saveCustomerRequest);
    SaveCustomerResponse toSaveCustomerResponse(Customer customer);
    SaveCustomerDto toSaveCustomerResponse(SaveCustomerRequest saveCustomerRequest);
}
// CustomerDto toCustomerDto(Customer customer);
//    @Mapping(target = "orders", ignore = true)
//    Customer toCustomer(CustomerDto customerDto);
//    @Mapping(target = "id", ignore = true)
//    CustomerDto toCustomerDto(SaveCustomerRequest saveCustomerRequest);
//    CustomerDto toUpdateCustomerResponse(UpdateCustomerRequest updateCustomerRequest);
//    CustomerResponse toCustomerResponse(Customer customer);
//    UpdateCustomerResponse toUpdateCustomerResponse(Customer customer);