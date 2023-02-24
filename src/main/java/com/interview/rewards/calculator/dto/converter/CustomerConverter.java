package com.interview.rewards.calculator.dto.converter;

import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.dto.TransactionDTO;
import com.interview.rewards.calculator.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**

 A utility class for converting Customer entity to CustomerDTO.
 */
public class CustomerConverter {

    /**

     Converts Customer entity to CustomerDTO
     @param customer The Customer entity to convert.
     @return The corresponding CustomerDTO.
     */
    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null)
            return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerName(customer.getName());
        dto.setMonthlyRewards(customer.getMonthlyRewards());
        dto.setTotalRewards(customer.getTotalRewards());
        List<TransactionDTO> transactionDTOs = new ArrayList<>();
        if(customer.getTransactions() != null) {
            transactionDTOs = customer.getTransactions()
                    .stream()
                    .map(TransactionConverter::toDTO)
            .collect(Collectors.toList());
        }
        dto.setTransactions(transactionDTOs);
        return dto;
    }

}