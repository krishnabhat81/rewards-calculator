package com.interview.rewards.calculator.service;

import com.interview.rewards.calculator.constants.RewardsCalculatorConstants;
import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.dto.converter.CustomerConverter;
import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import com.interview.rewards.calculator.model.Customer;
import com.interview.rewards.calculator.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**

 Service class responsible for calculating rewards for customers based on their transaction history.
 The calculation is based on the reward points earned by customers for each transaction over a certain transaction amount threshold.
 The service uses the CustomerService and TransactionService to access the data in the database and perform the calculations.
 */
@Service
public class RewardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardService.class);

    private CustomerService customerService;
    private TransactionService transactionService;

    /**

     Constructor for RewardService class.
     It initializes the dependencies required by this service.
     @param customerService The CustomerService object used for accessing customer data.
     @param transactionService The TransactionService object used for accessing transaction data.
     */
    public RewardService(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    /**

     Method that returns all customers and their reward points based on their transaction history.
     @return List of CustomerDTO objects containing each customer's reward points.
     @throws InternalServerErrorException if an unexpected error occurs while calculating rewards.
     */
    public List<CustomerDTO> getAllRewards() throws InternalServerErrorException {
        try {
            // Throw NotFoundException if no transactions are found
            List<Transaction> transactions = transactionService.findAll();
            if (transactions == null) {
                throw new NotFoundException("Transactions not found");
            }

            // Calculate rewards for each customer using their transaction history
            List<Customer> customers = calculateRewards(transactions);

            // Convert Customer objects to CustomerDTO objects and return
            return customers.stream()
                    .map(CustomerConverter::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception ex) {
            LOGGER.error("Error calculating rewards", ex);
            throw new InternalServerErrorException("Unexpected error occurred", ex);
        }
    }

    /**

     Method that returns a specific customer and their reward points based on their transaction history.
     @param customerId The ID of the customer to retrieve.
     @return CustomerDTO object containing the customer's reward points.
     @throws NotFoundException if no customer is found with the given ID.
     @throws InternalServerErrorException if an unexpected error occurs while calculating rewards.
     */
    public CustomerDTO getRewardsByCustomer(Long customerId) throws NotFoundException, InternalServerErrorException {
        Optional<Customer> customer = customerService.findById(customerId);
        customer.orElseThrow(() -> new NotFoundException("Customer not found with Id: " + customerId));
        calculateRewards(customer.get().getTransactions());
        return CustomerConverter.toDTO(customer.get());
    }

    /**

     Method that calculates rewards for all transactions provided.
     @param transactions The List of Transaction objects containing the transaction history of customers.
     @return List of Customer objects containing each customer's reward points.
     @throws InternalServerErrorException if an unexpected error occurs while calculating rewards.
     */
    List<Customer> calculateRewards(List<Transaction> transactions) throws InternalServerErrorException {

        Map<Long, Customer> customerMap = new HashMap<>();

        try {
            for (Transaction transaction : transactions) {
                Long customerId = transaction.getCustomer().getId();
                Customer customer = customerMap.get(customerId);
                if (customer == null) {
                    customer = customerService.findById(customerId).orElse(null);
                    customerMap.put(customerId, customer);
                }
                addRewardPoints(customer, transaction);
            }
        } catch (Exception ex) {
            LOGGER.error("Error calculating rewards", ex);
            throw new InternalServerErrorException("Unexpected error occurred", ex);
        }

        // Return a list of customers with updated reward points
        return new ArrayList<>(customerMap.values());
    }

    /**
     * Helper method to calculate reward points for a customer based on their transactions
     *
     * @param customer     The customer whose reward points are being calculated
     * @param transaction  The transaction for which reward points are being calculated
     */
    void addRewardPoints(Customer customer, Transaction transaction) {

        //Note: that this implementation assumes that the transaction amount is a positive value.

        BigDecimal amount = transaction.getAmount();
        int rewards = 0;
        if (amount.compareTo(RewardsCalculatorConstants.REWARD_THRESHOLD_2) >= 0) {
            rewards = RewardsCalculatorConstants.REWARD_RATE_2 * (amount.intValue() - 100) + RewardsCalculatorConstants.REWARD_RATE_1 * 50;
        }
        /*
         * NOTE: The assignment only asked for transaction amount over $100 but not about from $0 to $100
         * Assumption: customer will only get 50 points if amount is in range $50(inclusive) to $100(exclusive)
         * */
        else if (amount.compareTo(RewardsCalculatorConstants.REWARD_THRESHOLD_1) >= 0 && amount.compareTo(RewardsCalculatorConstants.REWARD_THRESHOLD_2) < 0) {
            rewards = RewardsCalculatorConstants.REWARD_RATE_1 * 50;
        }
        customer.addRewards(rewards, LocalDate.parse(transaction.getDate()));
    }
}
