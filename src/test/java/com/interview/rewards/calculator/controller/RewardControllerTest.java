package com.interview.rewards.calculator.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.interview.rewards.calculator.dto.CustomerDTO;
import com.interview.rewards.calculator.exception.InternalServerErrorException;
import com.interview.rewards.calculator.exception.NotFoundException;
import com.interview.rewards.calculator.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class RewardControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RewardController rewardController;

    @Mock
    private RewardService rewardService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RewardController(rewardService)).build();
    }

    @Test
    @DisplayName("Test GET /rewards returns rewards information for all customers")
    public void testGetRewards() throws Exception {
        List<CustomerDTO> customers = new ArrayList<>();
        customers.add(new CustomerDTO("Alice", null, null, 50));
        customers.add(new CustomerDTO("Bob", null, null, 100));

        when(rewardService.getAllRewards()).thenReturn(customers);

        mockMvc.perform(get("/rewards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName").value("Alice"))
                .andExpect(jsonPath("$[0].totalRewards").value(50))
                .andExpect(jsonPath("$[1].customerName").value("Bob"))
                .andExpect(jsonPath("$[1].totalRewards").value(100));
    }

    @Test
    @DisplayName("Test GET /rewards/{customerId} returns rewards information for a specific customer")
    public void testGetRewardsByCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO("Alice", null, null, 50);

        when(rewardService.getRewardsByCustomer(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/rewards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Alice"))
                .andExpect(jsonPath("$.totalRewards").value(50));
    }

    @Test
    void testGetRewardsByCustomerNotFound() throws NotFoundException, InternalServerErrorException {
        when(rewardService.getRewardsByCustomer(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> rewardController.getRewardsByCustomer(1L));
    }

    @Test
    void testGetRewardsInternalServerError() throws NotFoundException, InternalServerErrorException {
        when(rewardService.getAllRewards()).thenThrow(InternalServerErrorException.class);

        assertThrows(InternalServerErrorException.class, () -> rewardController.getRewards());
    }
}