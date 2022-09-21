package com.rewards.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private @NonNull String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private @NonNull BigDecimal amount;

}
