package com.zxcjabka.finansyio.controller;

import com.zxcjabka.finansyio.service.TransactionService;
import com.zxcjabka.finansyio.service.dto.TransactionDto;
import com.zxcjabka.finansyio.service.dto.model.TransactionCreationModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionCreationModel transaction) {
        TransactionDto transactionDto = transactionService.createTransaction(transaction);
        return ResponseEntity.ok().body(transactionDto);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getTransactions(@RequestParam("balance_name") String balanceName) {
        List<TransactionDto> transactions = transactionService.getTransactionsForBalance(balanceName);
        return ResponseEntity.ok().body(transactions);
    }

}
