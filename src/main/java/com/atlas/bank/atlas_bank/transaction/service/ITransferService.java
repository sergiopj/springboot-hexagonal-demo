package com.atlas.bank.atlas_bank.transaction.service;

import java.math.BigDecimal;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;

public interface ITransferService {

    Transaction execute(Long fromId, Long toId, BigDecimal amount);

}
