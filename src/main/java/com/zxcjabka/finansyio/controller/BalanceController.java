package com.zxcjabka.finansyio.controller;

import com.zxcjabka.finansyio.service.BalanceService;
import com.zxcjabka.finansyio.service.dto.BalanceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBalance(@RequestParam(name = "balance_name") String balanceName){
        BalanceDto balance = balanceService.createBalance(balanceName);
        return ResponseEntity.ok().body(balance);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getBalance(@RequestParam(name = "balance_name") String balanceName){
        BalanceDto balance  = balanceService.getBalance(balanceName);
        return ResponseEntity.ok().body(balance);
    }

}
