package com.zxcjabka.finansyio.util.mapper;

import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import com.zxcjabka.finansyio.service.dto.BalanceDto;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper {

    public BalanceDto toDto(BalanceEntity balanceEntity) {
        return BalanceDto.builder()
                .Id(balanceEntity.getId())
                .balance(balanceEntity.getBalance())
                .name(balanceEntity.getName())
                .build();
    }
}
