package org.example.store.storecustomer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Entity class representing a customer in the system.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "customers")
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    // Uncomment if orders relationship is needed
    // @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    // @ToString.Exclude
    // @EqualsAndHashCode.Exclude
    // private List<Order> orders;
}