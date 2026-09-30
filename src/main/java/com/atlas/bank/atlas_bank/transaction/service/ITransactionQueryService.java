package com.atlas.bank.atlas_bank.transaction.service;

import java.util.List;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;

public interface ITransactionQueryService {

    public List<Transaction> getAccountById(Long accountId);

}
