package com.zxcjabka.finansyio.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zxcjabka.finansyio.persistence.entity.Currency;
import com.zxcjabka.finansyio.persistence.entity.TransactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {
    @JsonProperty("id")
    UUID Id;
    @JsonProperty("transaction_type")
    TransactionType transactionType;
    @JsonProperty("amount")
    BigDecimal amount;
    @JsonProperty("currency")
    Currency currency;
    @JsonProperty()
    LocalDateTime timestamp;
    String balanceName;
}
