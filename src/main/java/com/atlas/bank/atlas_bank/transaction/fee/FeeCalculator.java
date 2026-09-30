package com.atlas.bank.atlas_bank.transaction.fee;

import java.math.BigDecimal;

public interface FeeCalculator {
    boolean supports(String accountType);

    BigDecimal calculate(BigDecimal amount);
}
