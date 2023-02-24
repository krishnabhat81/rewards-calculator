package com.interview.rewards.calculator.dto.converter;

import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.model.Customer;
import com.interview.rewards.calculator.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerConverterTest {

    @Test
    void testToDtoWithNullCustomer() {
        assertNull(CustomerConverter.toDTO(null));
    }

    @Test
    void testToDtoWithNullTransactions() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setTransactions(null);

        CustomerDTO dto = CustomerConverter.toDTO(customer);

        assertEquals("John Doe", dto.getCustomerName());
        assertTrue(dto.getTransactions().isEmpty());
    }

    @Test
    void testToDtoWithEmptyTransactions() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setTransactions(Collections.emptyList());

        CustomerDTO dto = CustomerConverter.toDTO(customer);

        assertEquals("John Doe", dto.getCustomerName());
        assertEquals(0, dto.getTotalRewards());
        assertTrue(dto.getTransactions().isEmpty());
    }

    @Test
    void testToDtoWithSingleTransaction() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setDate("2022-01-01");
        customer.setTransactions(Collections.singletonList(transaction));

        CustomerDTO dto = CustomerConverter.toDTO(customer);

        assertEquals("John Doe", dto.getCustomerName());
        assertEquals(1, dto.getTransactions().size());
        assertEquals(BigDecimal.valueOf(100), dto.getTransactions().get(0).getAmount());
        assertEquals("2022-01-01", dto.getTransactions().get(0).getDate());
    }

    @Test
    void testToDtoWithMultipleTransactions() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, customer, BigDecimal.valueOf(50), "2022-01-01"));
        transactions.add(new Transaction(2L, customer, BigDecimal.valueOf(100), "2022-02-01"));
        customer.setTransactions(transactions);

        CustomerDTO dto = CustomerConverter.toDTO(customer);

        assertEquals("John Doe", dto.getCustomerName());
        assertEquals(2, dto.getTransactions().size());
        assertEquals(BigDecimal.valueOf(50), dto.getTransactions().get(0).getAmount());
        assertEquals("2022-01-01", dto.getTransactions().get(0).getDate());
        assertEquals(BigDecimal.valueOf(100), dto.getTransactions().get(1).getAmount());
        assertEquals("2022-02-01", dto.getTransactions().get(1).getDate());
    }

}