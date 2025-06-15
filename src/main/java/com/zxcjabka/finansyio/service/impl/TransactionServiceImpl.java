package com.zxcjabka.finansyio.service.impl;

import com.zxcjabka.finansyio.converter.CurrencyConverter;
import com.zxcjabka.finansyio.exception.BalanceNotFoundException;
import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import com.zxcjabka.finansyio.persistence.entity.Currency;
import com.zxcjabka.finansyio.persistence.entity.TransactionEntity;
import com.zxcjabka.finansyio.persistence.repository.BalanceRepository;
import com.zxcjabka.finansyio.persistence.repository.TransactionRepository;
import com.zxcjabka.finansyio.service.TransactionService;
import com.zxcjabka.finansyio.service.dto.TransactionDto;
import com.zxcjabka.finansyio.service.dto.model.TransactionCreationModel;
import com.zxcjabka.finansyio.util.mapper.TransactionMapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;
    private final TransactionMapper transactionMapper;
    private final CurrencyConverter currencyConverter;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  BalanceRepository balanceRepository,
                                  TransactionMapper transactionMapper,
                                  CurrencyConverter currencyConverter
                                  ){
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
        this.transactionMapper = transactionMapper;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public TransactionDto createTransaction(TransactionCreationModel transaction) {
        BalanceEntity balance = balanceRepository.findBalanceEntityByName(transaction.getBalanceName())
                .orElseThrow(() -> new BalanceNotFoundException(
                        String.format("Balance with name %s does not exist", transaction.getBalanceName())
                ));

        TransactionEntity transactionEntity = transactionMapper.toEntity(transaction, balance);
        processTransaction(transactionEntity);

        transactionEntity = transactionRepository.saveAndFlush(transactionEntity);
        return transactionMapper.toDto(transactionEntity);
    }

    @Override
    public List<TransactionDto> getTransactionsForBalance(String balanceName) {
        BalanceEntity balance = balanceRepository.findBalanceEntityByName(balanceName)
                .orElseThrow(() -> new BalanceNotFoundException(String.format("Balance with name %s does not exist", balanceName)));
        List<TransactionEntity> transactionEntities = transactionRepository.findAllByBalanceOrderByTimestampDesc(balance);
        return transactionEntities.stream().map(transactionMapper::toDto).toList();

    }

    public void processTransaction(TransactionEntity transactionEntity) {
        switch (transactionEntity.getTransactionType()) {
            case DEPOSIT -> processDepositTransaction(transactionEntity);
            case WITHDRAW -> processWithdrawTransaction(transactionEntity);
            default -> throw new IllegalArgumentException("Unknown transaction type");
        }
    }

    public void processWithdrawTransaction(TransactionEntity transactionEntity) {
        BigDecimal amountInUsd = convertToUsd(transactionEntity);
        BalanceEntity balance = transactionEntity.getBalance();

        if (balance.getBalance().compareTo(amountInUsd) < 0) {
            throw new IllegalArgumentException(
                    String.format("Insufficient funds. Balance: %s, Required: %s",
                            balance.getBalance(), amountInUsd)
            );
        }

        balance.setBalance(balance.getBalance().subtract(amountInUsd));
        balance = balanceRepository.saveAndFlush(balance);
        transactionEntity.setBalance(balance);
    }

    public void processDepositTransaction(TransactionEntity transactionEntity) {
        BigDecimal amountInUsd = convertToUsd(transactionEntity);
        BalanceEntity balance = transactionEntity.getBalance();

        balance.setBalance(balance.getBalance().add(amountInUsd));
        balance = balanceRepository.saveAndFlush(balance);
        transactionEntity.setBalance(balance);
    }

    public BigDecimal convertToUsd(TransactionEntity transaction) {
        return currencyConverter.convert(
                transaction.getAmount(),
                transaction.getCurrency(),
                Currency.USD
        );
    }
}