package com.atlas.bank.atlas_bank.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.account.dto.AccountResponse;
import com.atlas.bank.atlas_bank.account.dto.CreateAccountRequest;
import com.atlas.bank.atlas_bank.account.model.Account;
import com.atlas.bank.atlas_bank.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountResponse create(CreateAccountRequest request) {
        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setOwnerName(request.getOwnerName());
        account.setEmail(request.getEmail());
        account.setType(request.getType());
        account.setBalance(request.getBalance());
        account.setStatus("ACTIVE");

        Account saved = accountRepository.save(account);

        return toResponse(saved);
    }

    @Override
    public List<AccountResponse> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public AccountResponse findById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return toResponse(account);
    }

    private AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .ownerName(account.getOwnerName())
                .email(account.getEmail())
                .type(account.getType())
                .status(account.getStatus())
                .balance(account.getBalance())
                .build();
    }

}