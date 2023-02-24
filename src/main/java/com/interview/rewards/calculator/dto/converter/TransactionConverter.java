package com.interview.rewards.calculator.dto.converter;

import com.interview.rewards.calculator.dto.TransactionDTO;
import com.interview.rewards.calculator.model.Transaction;

/**

 A utility class for converting Transaction entity to TransactionDTO.
 */
public class TransactionConverter {

    /**

     Converts Transaction entity to TransactionDTO
     @param transaction The Transaction entity to convert.
     @return The corresponding TransactionDTO.
     */
    public static TransactionDTO toDTO(Transaction transaction) {
        if (transaction == null)
            return null;
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setDate(transaction.getDate());
        return transactionDTO;
    }
}
