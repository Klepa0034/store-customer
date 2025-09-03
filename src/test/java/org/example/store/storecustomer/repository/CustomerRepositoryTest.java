package org.example.store.storecustomer.repository;

import org.example.store.storecustomer.container.TestContainerConfig;
import org.example.store.storecustomer.entity.Customer;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

//todo @SpringBootTest чтобы поднять весь контекст
@DataJpaTest //TODO Поднимаю только контекст где есть бины для теста бд
@ActiveProfiles("test")
@Import(TestContainerConfig.class)
@ImportAutoConfiguration(LiquibaseAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @DisplayName("deletebyid")
    public void deleteById(){
        long id = 1L;

        Optional<Customer> beforeDelete = customerRepository.findById(id);
        Optional<Customer> afterDelete = customerRepository.findById(id);

        System.out.println(beforeDelete);
        System.out.println(afterDelete);
    }
    @Test
    @DisplayName("findall")
    public void findAll(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println(customers);
    }
    @Test
    @DisplayName("save")
    public void save(){
        Customer customer = Instancio.of(Customer.class).ignore(field(Customer::getId)).create();

        Customer saved = customerRepository.save(customer);

        assertThat(saved).isNotNull();
    }
}
//todo unit test-тесты одного слоя, в котором нет внешней системы
//todo есть интеграционные тесты, где теститься внешняя система
//todo тест для контролера,сервиса,маппера - юнит, интеграционные тесты для бд репозитроий и полностью всё  