package com.rewards.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "customer")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CustomerEntity implements Serializable {

    @Id
    @Column(name = "customer_id")
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 100)
    @NonNull
    private String firstName;

    @Column(name = "order_amount", nullable = true)
    @NonNull
    private BigDecimal amount;


}
