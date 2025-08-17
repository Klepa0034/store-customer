package org.example.store.storecustomer.dto.api;

import lombok.*;

import java.math.BigDecimal;
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaveCustomerResponse {
    private String name;
    private BigDecimal balance;
}
