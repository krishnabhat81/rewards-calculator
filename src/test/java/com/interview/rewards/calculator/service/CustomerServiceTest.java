package com.interview.rewards.calculator.service;

import com.interview.rewards.calculator.model.Customer;
import com.interview.rewards.calculator.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testSave() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.save(customer);

        assertEquals(customer, savedCustomer);
        verify(customerRepository).save(customer);
    }

    @Test
    void testSaveAll() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Jane Doe");

        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.saveAll(anyIterable())).thenReturn(customers);

        List<Customer> savedCustomers = customerService.saveAll(customers);

        // Assert
        assertEquals(customers, savedCustomers);
        verify(customerRepository).saveAll(customers);
    }

    @Test
    void testFindAll() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("John Doe");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Jane Doe");

        customers.add(customer1);
        customers.add(customer2);

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> foundCustomers = customerService.findAll();

        // Assert
        assertEquals(customers, foundCustomers);
        verify(customerRepository).findAll();
    }

    @Test
    void testFindById() {
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Optional<Customer> foundCustomer = customerService.findById(customerId);

        // Assert
        assertTrue(foundCustomer.isPresent());
        assertEquals(customer, foundCustomer.get());
        verify(customerRepository).findById(customerId);
    }

    @Test
    void testDeleteById() {
        long customerId = 1L;

        customerService.deleteById(customerId);

        // Assert
        verify(customerRepository).deleteById(customerId);
    }

}