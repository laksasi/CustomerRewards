package com.rewards.controller;

import com.rewards.entity.CustomerEntity;
import com.rewards.model.Customer;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.RewardService;
import io.swagger.annotations.ApiImplicitParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@RestController("rewards")
public class CustomerRewardsController {

    @Autowired
    private RewardService rewardService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/customer")
    public Customer createCustomerOrder(@RequestBody Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer object can not be null");
        }
        CustomerEntity customerEntity = modelMapper.map(customer, CustomerEntity.class);
        return modelMapper.map(rewardService.save(customerEntity), Customer.class);
    }

    @GetMapping("/customer/{id}")
    @ApiImplicitParam(name="id",value = "",required = true,dataType = "int",paramType = "path",allowMultiple = false)
    public BigDecimal getRewardPointsByCustomerId(@PathVariable("id") Integer customerId) {
        CustomerEntity customerEntity = rewardService.
                getCustomerEntityById(customerId)
                .orElseThrow(EntityNotFoundException::new);
        return rewardService.calculateRewards(customerEntity);
    }
}