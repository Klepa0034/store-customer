package org.example.store.storecustomer.mapper;

import org.example.store.storecustomer.dto.api.*;
import org.example.store.storecustomer.dto.service.AllCustomerResponseDto;
import org.example.store.storecustomer.dto.service.SaveCustomerRequestDto;
import org.example.store.storecustomer.dto.service.UpdateCustomerRequestDto;
import org.example.store.storecustomer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * Mapper for converting between Customer entities and DTOs.
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer toEntity(SaveCustomerRequestDto saveCustomerRequestDto);

    SaveCustomerResponse toSaveResponseFromEntity(Customer customer);

    SaveCustomerRequestDto toSaveRequestDtoFromRequest(SaveCustomerRequest saveCustomerRequest);

}
