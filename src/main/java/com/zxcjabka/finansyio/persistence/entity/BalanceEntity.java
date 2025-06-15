package com.zxcjabka.finansyio.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    String name;
    BigDecimal balance = BigDecimal.ZERO;
    Currency currency = Currency.USD;
    public BalanceEntity(String balanceName) {
        this.name = balanceName;
    }
}
