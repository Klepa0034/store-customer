package org.example.store.storecustomer.repository;

import org.example.store.storecustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Customer entity operations.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}