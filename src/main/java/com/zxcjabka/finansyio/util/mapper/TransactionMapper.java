package com.zxcjabka.finansyio.util.mapper;

import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import com.zxcjabka.finansyio.persistence.entity.TransactionEntity;
import com.zxcjabka.finansyio.service.dto.TransactionDto;
import com.zxcjabka.finansyio.service.dto.model.TransactionCreationModel;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(TransactionCreationModel transaction, BalanceEntity balance) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setCurrency(transaction.getCurrency());
        transactionEntity.setBalance(balance);
        transactionEntity.setTransactionType(transaction.getTransactionType());
        return transactionEntity;
    }

    public TransactionDto toDto(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .Id(transactionEntity.getId())
                .transactionType(transactionEntity.getTransactionType())
                .currency(transactionEntity.getCurrency())
                .amount(transactionEntity.getAmount())
                .timestamp(transactionEntity.getTimestamp())
                .balanceName(transactionEntity.getBalance().getName())
                .build();

    }
}
