package com.atlas.bank.atlas_bank.transaction.service;

import java.util.List;

import com.atlas.bank.atlas_bank.transaction.dto.TransactionResponse;
import com.atlas.bank.atlas_bank.transaction.dto.TransferRequest;

public interface ITransferService {

    TransactionResponse execute(TransferRequest request);

}
