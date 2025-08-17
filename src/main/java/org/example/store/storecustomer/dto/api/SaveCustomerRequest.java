package org.example.store.storecustomer.dto.api;

import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaveCustomerRequest {
    private String name;
    private BigDecimal balance;
}
