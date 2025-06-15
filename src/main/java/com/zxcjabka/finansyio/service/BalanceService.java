package com.zxcjabka.finansyio.service;

import com.zxcjabka.finansyio.service.dto.BalanceDto;

public interface BalanceService {

    BalanceDto createBalance(String balanceName);

    BalanceDto getBalance(String balanceName);
}
