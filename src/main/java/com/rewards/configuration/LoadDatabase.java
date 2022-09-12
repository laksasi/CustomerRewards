package com.rewards.configuration;

import com.rewards.entity.CustomerEntity;
import com.rewards.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            CustomerEntity customer = new CustomerEntity("John", BigDecimal.valueOf(200.01));
            log.info("Preloading " + repository.save(customer));
        };
    }
}
