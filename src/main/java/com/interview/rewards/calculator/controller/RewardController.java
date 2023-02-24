package com.interview.rewards.calculator.controller;

import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import com.interview.rewards.calculator.service.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**

 REST controller class that handles incoming HTTP requests related to customer rewards and
 communicates with the RewardService to process those requests and return the appropriate response.
*/
@RestController()
public class RewardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardController.class);

    private final RewardService rewardService;

    /**

     Constructor to initialize RewardController with RewardService dependency injection.
     @param rewardService RewardService object to handle rewards related business logic.
     */
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**

     Handles GET request to retrieve rewards information for all customers.
     @return List of CustomerDTO objects representing rewards information for all customers.
     @throws InternalServerErrorException if an unexpected error occurs while processing the request.
     */
    @GetMapping("/rewards")
    public List<CustomerDTO> getRewards() throws InternalServerErrorException {
        return rewardService.getAllRewards();
    }

    /**

     Handles GET request to retrieve rewards information for a specific customer.
     @param customerId ID of the customer whose rewards information is to be retrieved.
     @return CustomerDTO object representing rewards information for the specified customer.
     @throws NotFoundException if the specified customer is not found.
     @throws InternalServerErrorException if an unexpected error occurs while processing the request.
     */
    @GetMapping("/rewards/{customerId}")
    public CustomerDTO getRewardsByCustomer(@PathVariable long customerId) throws NotFoundException, InternalServerErrorException {
        return rewardService.getRewardsByCustomer(customerId);
    }
}
