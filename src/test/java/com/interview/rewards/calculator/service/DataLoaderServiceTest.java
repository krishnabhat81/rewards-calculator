package com.interview.rewards.calculator.service;

import com.interview.rewards.calculator.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataLoaderServiceTest {

    @Mock
    private CustomerService customerService;

    private DataLoaderService dataLoaderService;

    @BeforeEach
    public void setUp() {
        dataLoaderService = new DataLoaderService(customerService);
        // mock behavior of customerService.saveAll()
        when(customerService.saveAll(anyList())).thenReturn(new ArrayList<>());
    }

    @Test
    public void testLoadData_Generates50Customers() throws IOException {
        dataLoaderService.loadData();

        // Assert
        verify(customerService, times(1)).saveAll(Mockito.anyList());
        ArgumentCaptor<List<Customer>> captor = ArgumentCaptor.forClass(List.class);
        verify(customerService).saveAll(captor.capture());
        List<Customer> savedCustomers = captor.getValue();
        assertEquals(50, savedCustomers.size());
    }

    @Test
    public void testLoadData_GeneratesTransactionsForAllCustomers() throws IOException {
        dataLoaderService.loadData();

        // Assert
        verify(customerService, times(1)).saveAll(Mockito.anyList());
        ArgumentCaptor<List<Customer>> captor = ArgumentCaptor.forClass(List.class);
        verify(customerService).saveAll(captor.capture());
        List<Customer> savedCustomers = captor.getValue();
    }
}