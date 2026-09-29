package com.atlas.bank.atlas_bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.model.Transaction;
import com.atlas.bank.atlas_bank.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionQueryService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getAccountById(Long accountId) {
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }

}
