package com.rewards.service;

import com.rewards.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RewardService {

    public BigDecimal calculateRewards(CustomerEntity customerEntity) {
        BigDecimal purchaseAmount = customerEntity.getAmount();
        BigDecimal hundredDollars = BigDecimal.valueOf(100.00);
        BigDecimal fiftyDollars = BigDecimal.valueOf(50.00);
        BigDecimal diff = BigDecimal.valueOf(0.00);

        return purchaseAmount.compareTo(hundredDollars) > 0
                ? purchaseAmount.subtract(hundredDollars).multiply(BigDecimal.valueOf(2)).add(fiftyDollars)
                : purchaseAmount.compareTo(fiftyDollars) > 0
                ? purchaseAmount.subtract(fiftyDollars).multiply(BigDecimal.valueOf(1)) : diff;
    }
}
