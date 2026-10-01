package com.atlas.bank.atlas_bank.transaction.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.transaction.dto.TransactionResponse;
import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionQueryService implements ITransactionQueryService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> getTransactionsByAccountId(Long accountId) {
        return transactionRepository
                .findBySourceAccountIdOrTargetAccountId(accountId, accountId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private TransactionResponse toResponse(Transaction t) {
        return TransactionResponse.builder()
                .id(t.getId())
                .type(t.getType())
                .sourceAccountId(t.getSourceAccountId())
                .targetAccountId(t.getTargetAccountId())
                .amount(t.getAmount())
                .fee(t.getFee())
                .status(t.getStatus())
                .createdAt(t.getCreatedAt())
                .build();
    }

}
