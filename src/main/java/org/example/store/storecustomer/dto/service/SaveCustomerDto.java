package org.example.store.storecustomer.dto.service;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaveCustomerDto {
    private String name;
    private BigDecimal balance;
}
