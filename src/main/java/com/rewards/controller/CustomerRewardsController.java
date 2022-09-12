package com.rewards.controller;

import com.rewards.entity.CustomerEntity;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.RewardService;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@RestController("rewards")
public class CustomerRewardsController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customer")
    public CustomerEntity createCustomerOrder(@RequestBody CustomerEntity customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer object can not be null");
        }
        return customerRepository.save(customer);
    }

    @GetMapping("/customer/{id}")
    @ApiImplicitParam(name="id",value = "",required = true,dataType = "int",paramType = "path",allowMultiple = false)
    public BigDecimal getRewardPointsByCustomerId(@PathVariable("id") Integer customerId) {
        CustomerEntity customerEntity = customerRepository.
                findById(customerId)
                .orElseThrow(EntityNotFoundException::new);
        return rewardService.calculateRewards(customerEntity);
    }
}