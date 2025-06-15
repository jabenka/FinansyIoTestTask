package com.zxcjabka.finansyio.service.impl;

import com.zxcjabka.finansyio.exception.BalanceAlreadyExistsException;
import com.zxcjabka.finansyio.exception.BalanceNotFoundException;
import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import com.zxcjabka.finansyio.persistence.repository.BalanceRepository;
import com.zxcjabka.finansyio.service.BalanceService;
import com.zxcjabka.finansyio.service.dto.BalanceDto;
import com.zxcjabka.finansyio.util.mapper.BalanceMapper;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;

    public BalanceServiceImpl(BalanceRepository balanceRepository, BalanceMapper balanceMapper) {
        this.balanceRepository = balanceRepository;
        this.balanceMapper = balanceMapper;
    }


    @Override
    public BalanceDto createBalance(String balanceName) {
        BalanceEntity balanceEntity = new BalanceEntity(balanceName);
        if(balanceRepository.existsBalanceEntitiesByName(balanceName)) {
            throw new BalanceAlreadyExistsException("Balance with name " + balanceName + " already exists");
        }
        balanceEntity = balanceRepository.saveAndFlush(balanceEntity);
        return balanceMapper.toDto(balanceEntity);
    }

    @Override
    public BalanceDto getBalance(String balanceName) {
        BalanceEntity balanceEntity = balanceRepository.findBalanceEntityByName(balanceName).orElseThrow(
                () -> new BalanceNotFoundException("Balance with name " + balanceName + " not found")
        );
        return balanceMapper.toDto(balanceEntity);
    }
}
