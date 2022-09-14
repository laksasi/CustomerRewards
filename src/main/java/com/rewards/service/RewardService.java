package com.rewards.service;

import com.rewards.entity.CustomerEntity;
import com.rewards.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class RewardService {

    @Autowired
    private CustomerRepository customerRepository;

    /**+
     * Method to  calculate reward points based on the purchase amount.
     * A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
     * dollar spent over $50 in each transaction
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     * @param customerEntity
     * @return BigDecimal
     */
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

    /**+
     * Method to saves customer purchase amount data to the database
     * @param customerEntity
     * @return CustomerEntity
     */
    public CustomerEntity save(CustomerEntity customerEntity){
        return customerRepository.save(customerEntity);
    }

    /**+
     * Method to retrieve customer data by customr ID
     * @param id
     * @return CustomerEntity
     */
    public Optional<CustomerEntity> getCustomerEntityById(Integer id){
        return customerRepository.findById(id);
    }
}
