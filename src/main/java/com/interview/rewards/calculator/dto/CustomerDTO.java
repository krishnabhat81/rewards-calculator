package com.interview.rewards.calculator.dto;

import java.util.List;
import java.util.Map;

/**
 * A data transfer object representing a customer and their associated transaction information.
 */
public class CustomerDTO {

    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * The total rewards earned by the customer for all transactions.
     */
    private int totalRewards;

    /**
     * A map containing the rewards earned by the customer for each month.
     * The key is a string representing the month in "YYYY-MM" format.
     */
    private Map<String, Integer> monthlyRewards;

    /**
     * The list of transactions associated with the customer.
     */
    private List<TransactionDTO> transactions;

    public CustomerDTO() {}

    public CustomerDTO(String customerName, List<TransactionDTO> transactions, Map<String, Integer> monthlyRewards, int totalRewards) {
        this.customerName = customerName;
        this.transactions = transactions;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public Map<String, Integer> getMonthlyRewards() {
        return monthlyRewards;
    }

    public void setMonthlyRewards(Map<String, Integer> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(int totalRewards) {
        this.totalRewards = totalRewards;
    }
}