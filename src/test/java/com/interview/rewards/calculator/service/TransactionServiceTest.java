package com.interview.rewards.calculator.service;

import com.interview.rewards.calculator.model.Transaction;
import com.interview.rewards.calculator.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testSave() {
        Transaction transaction = new Transaction();
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDate(LocalDate.now().toString());

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction savedTransaction = transactionService.save(transaction);

        assertNotNull(savedTransaction);
        assertEquals(transaction.getAmount(), savedTransaction.getAmount());
        assertEquals(transaction.getDate(), savedTransaction.getDate());
    }

    @Test
    void testFindAll() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setAmount(BigDecimal.TEN);
        transaction1.setDate(LocalDate.now().toString());

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setAmount(BigDecimal.valueOf(20));
        transaction2.setDate(LocalDate.now().minusDays(1).toString());

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> transactions = transactionService.findAll();

        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals(transaction1.getAmount(), transactions.get(0).getAmount());
        assertEquals(transaction2.getAmount(), transactions.get(1).getAmount());
    }

    @Test
    void testFindById() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDate(LocalDate.now().toString());

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Optional<Transaction> foundTransaction = transactionService.findById(1L);

        assertTrue(foundTransaction.isPresent());
        assertEquals(transaction.getAmount(), foundTransaction.get().getAmount());
        assertEquals(transaction.getDate(), foundTransaction.get().getDate());
    }

    @Test
    void testDeleteById() {
        doNothing().when(transactionRepository).deleteById(1L);

        transactionService.deleteById(1L);

        verify(transactionRepository, times(1)).deleteById(1L);
    }

}