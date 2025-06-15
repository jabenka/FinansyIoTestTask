package com.zxcjabka.finansyio.converter;

import com.zxcjabka.finansyio.persistence.entity.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Component
public class CurrencyConverterImpl implements CurrencyConverter {
    private static final Map<Currency, BigDecimal> EXCHANGE_RATES = Map.of(
            Currency.USD, BigDecimal.ONE,
            Currency.EUR, new BigDecimal("0.93"),
            Currency.BYN, new BigDecimal("0.40"),
            Currency.RUB, new BigDecimal("0.011")
    );

    @Override
    public BigDecimal convert(BigDecimal amount, Currency from, Currency to) {
        if (from == to) {
            return amount;
        }
        BigDecimal fromRate = EXCHANGE_RATES.get(from);
        BigDecimal toRate = EXCHANGE_RATES.get(to);
        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Unsupported currency");
        }
        return amount.multiply(fromRate).divide(toRate, 2, RoundingMode.HALF_UP);
    }
}
