package com.zxcjabka.finansyio.converter;

import com.zxcjabka.finansyio.persistence.entity.Currency;

import java.math.BigDecimal;

public interface CurrencyConverter {

    BigDecimal convert(BigDecimal amount, Currency from, Currency to);
}
