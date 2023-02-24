package com.interview.rewards.calculator.constants;

import java.math.BigDecimal;

public class RewardsCalculatorConstants {
    // Constants for reward calculation
    public static final BigDecimal REWARD_THRESHOLD_1 = new BigDecimal("50");
    public static final BigDecimal REWARD_THRESHOLD_2 = new BigDecimal("100");
    public static final int REWARD_RATE_1 = 1;
    public static final int REWARD_RATE_2 = 2;

    //Data loader constants
    public static final int NUM_CUSTOMERS = 50;
    public static final int TRANSACTION_AMOUNT_MAX = 250;
    public static final int MONTHS_TO_GENERATE = 3;
}
