package com.atlas.bank.atlas_bank.transaction.service;

import java.util.List;

import com.atlas.bank.atlas_bank.transaction.dto.TransactionResponse;

public interface ITransactionQueryService {

    List<TransactionResponse> getTransactionsByAccountId(Long accountId);

}
