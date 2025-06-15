package com.zxcjabka.finansyio.service.dto.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zxcjabka.finansyio.persistence.entity.Currency;
import com.zxcjabka.finansyio.persistence.entity.TransactionType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCreationModel {
    @JsonProperty("transaction_type")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TransactionType transactionType;
    @JsonProperty("amount")
    @DecimalMin("0.0")
    BigDecimal amount;
    @JsonProperty("currency")
    Currency currency;
    @JsonProperty("balance_name")
    String balanceName;
}
