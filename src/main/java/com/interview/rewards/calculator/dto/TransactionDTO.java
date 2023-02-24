package com.interview.rewards.calculator.dto;

import java.math.BigDecimal;

/**

 This class represents a DTO (Data Transfer Object) for the Transaction entity, which is used to transfer
 transaction data between the application layers.
 The class contains properties that match the fields of the Transaction entity, and is used to encapsulate
 transaction data in a structured format.
 Note that this class does not contain any business logic, and should only be used for data transfer purposes.
 */
public class TransactionDTO {

    private BigDecimal amount;

    private String date;

    public TransactionDTO() {}

    public TransactionDTO(BigDecimal amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    // getters and setters

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}