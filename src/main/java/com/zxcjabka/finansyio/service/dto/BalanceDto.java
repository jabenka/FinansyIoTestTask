package com.zxcjabka.finansyio.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceDto {
    @JsonProperty("id")
    UUID Id;
    @JsonProperty("balance_name")
    String name;
    @JsonProperty("balance")
    BigDecimal balance;
}
