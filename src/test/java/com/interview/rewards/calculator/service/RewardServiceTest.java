package com.interview.rewards.calculator.service;

import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import com.interview.rewards.calculator.model.Customer;
import com.interview.rewards.calculator.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private CustomerService customerService;

    @Mock
    private TransactionService transactionService;

    @Test
    public void testGetAllRewards() throws InternalServerErrorException {
        // mock TransactionService and CustomerService objects
        List<Transaction> transactions = new ArrayList<>();
        when(transactionService.findAll()).thenReturn(transactions);

        // create RewardService object and call getAllRewards method
        List<CustomerDTO> customerDTOs = rewardService.getAllRewards();

        // verify that the returned list is not null
        assertNotNull(customerDTOs);
    }

    @Test
    public void testGetAllRewardsWithEmptyTransactions() throws Exception {
        when(transactionService.findAll()).thenReturn(Collections.emptyList());

        assertThat(rewardService.getAllRewards()).isEmpty();
    }


    @Test
    public void testGetRewardsByCustomer() throws NotFoundException, InternalServerErrorException {
        // mock CustomerService object
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.findById(customerId)).thenReturn(Optional.of(customer));

        // mock Transaction object and call addRewardPoints method
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.valueOf(120));
        transaction.setDate("2022-01-01");
        rewardService.addRewardPoints(customer, transaction);

        // call getRewardsByCustomer method and verify that it returns a CustomerDTO object
        CustomerDTO customerDTO = rewardService.getRewardsByCustomer(customerId);
        assertNotNull(customerDTO);
    }

    @Test
    public void testGetRewardsByCustomerWithNonexistentCustomer() {
        long customerId = 1L;
        when(customerService.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rewardService.getRewardsByCustomer(customerId));
    }

    @Test
    public void testCalculateRewards() throws InternalServerErrorException {
        // mock CustomerService object
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerService.findById(customerId)).thenReturn(Optional.of(customer));

        // create a list of transactions and call calculateRewards method
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(BigDecimal.valueOf(120));
        transaction1.setDate("2022-01-01");
        transaction1.setCustomer(customer);
        transactions.add(transaction1);
        Transaction transaction2 = new Transaction();
        transaction2.setAmount(BigDecimal.valueOf(80));
        transaction2.setDate("2022-01-01");
        transaction2.setCustomer(customer);
        transactions.add(transaction2);
        List<Customer> customers = rewardService.calculateRewards(transactions);

        // verify that the returned list is not null and contains a Customer object with the correct ID
        assertNotNull(customers);
        assertEquals(1, customers.size());
        assertEquals(customerId, customers.get(0).getId());
    }

    @Test
    public void testGetAllRewardsWithException() throws Exception {
        when(transactionService.findAll()).thenReturn(Arrays.asList(
                new Transaction(1L, null, BigDecimal.TEN, LocalDate.now().toString())));

        RewardService rewardService = new RewardService(customerService, transactionService) {
            @Override
            public List<Customer> calculateRewards(List<Transaction> transactions) throws InternalServerErrorException {
                throw new InternalServerErrorException("Error in calculateRewards", new Exception());
            }
        };

        assertThrows(InternalServerErrorException.class, () -> rewardService.getAllRewards());
    }

    @Test
    public void testAddRewardPoints() throws Exception {
        Customer customer = new Customer(1L, "John", 0);
        Transaction transaction = new Transaction(1L, null, new BigDecimal("120"), LocalDate.now().toString());

        rewardService.addRewardPoints(customer, transaction);

        assertEquals(90, customer.getTotalRewards());
    }

}
