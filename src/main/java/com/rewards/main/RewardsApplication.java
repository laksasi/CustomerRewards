package com.rewards.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.rewards")
@EnableJpaRepositories(basePackages = "com.rewards.repository")
@EntityScan("com.rewards.entity")
public class RewardsApplication {

    public static void main(String... args) {
        SpringApplication.run(RewardsApplication.class, args);
    }
}

