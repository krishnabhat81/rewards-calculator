package com.interview.rewards.calculator.dto.converter;

import com.interview.rewards.calculator.dto.TransactionDTO;
import com.interview.rewards.calculator.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class TransactionConverterTest {
    @Test
    public void testToDTOWithNullTransaction() {
        TransactionDTO transactionDTO = TransactionConverter.toDTO(null);
        Assertions.assertNull(transactionDTO);
    }

    @Test
    public void testToDTOWithTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("100"));
        transaction.setDate(LocalDate.of(2022, 1, 1).toString());
        TransactionDTO transactionDTO = TransactionConverter.toDTO(transaction);
        Assertions.assertNotNull(transactionDTO);
        Assertions.assertEquals(new BigDecimal("100"), transactionDTO.getAmount());
        Assertions.assertEquals(LocalDate.of(2022, 1, 1).toString(), transactionDTO.getDate());
    }
}
