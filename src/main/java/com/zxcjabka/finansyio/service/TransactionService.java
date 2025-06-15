package com.zxcjabka.finansyio.service;

import com.zxcjabka.finansyio.service.dto.TransactionDto;
import com.zxcjabka.finansyio.service.dto.model.TransactionCreationModel;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(TransactionCreationModel transaction);

    List<TransactionDto> getTransactionsForBalance(String balanceName);
}
