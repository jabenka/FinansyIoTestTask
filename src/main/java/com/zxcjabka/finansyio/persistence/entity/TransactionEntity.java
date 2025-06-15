package com.zxcjabka.finansyio.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID Id;
    TransactionType transactionType;
    BigDecimal amount;
    Currency currency;
    @CreationTimestamp
    LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    BalanceEntity balance;
}
