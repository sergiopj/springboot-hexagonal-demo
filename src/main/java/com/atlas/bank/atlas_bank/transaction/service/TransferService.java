package com.atlas.bank.atlas_bank.transaction.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.account.model.Account;
import com.atlas.bank.atlas_bank.account.repository.AccountRepository;
import com.atlas.bank.atlas_bank.transaction.dto.TransferRequest;
import com.atlas.bank.atlas_bank.transaction.fee.FeeCalculator;
import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService implements ITransferService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final List<FeeCalculator> feeCalculators;

    @Override
    @Transactional
    public Transaction transfer(TransferRequest request) {
        Account from = accountRepository.findById(request.getSourceAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        Account to = accountRepository.findById(request.getTargetAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (!"ACTIVE".equals(from.getStatus())) {
            throw new RuntimeException("La cuenta origen no está activa");
        }
        if (!"ACTIVE".equals(to.getStatus())) {
            throw new RuntimeException("La cuenta destino no está activa");
        }

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Fondos insuficientes");
        }

        BigDecimal fee = feeCalculators.stream()
                .filter(fc -> fc.supports(from.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay calculador para el tipo " + from.getType()))
                .calculate(request.getAmount());

        from.setBalance(from.getBalance().subtract(request.getAmount()).subtract(fee));
        to.setBalance(to.getBalance().add(request.getAmount()));
        accountRepository.save(from);
        accountRepository.save(to);

        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(request.getSourceAccountId());
        transaction.setTargetAccountId(request.getTargetAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");

        return transactionRepository.save(transaction);
    }

}
