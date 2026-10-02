package com.atlas.bank.atlas_bank.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionQueryService implements ITransactionQueryService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }

}
