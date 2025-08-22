package org.example.store.storecustomer;

import org.springframework.boot.SpringApplication;

public class TestStoreCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.from(StoreCustomerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
