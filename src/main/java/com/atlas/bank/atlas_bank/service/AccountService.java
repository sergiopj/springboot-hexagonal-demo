package com.atlas.bank.atlas_bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.model.Account;
import com.atlas.bank.atlas_bank.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

// GOD service pues hace todo pero es muy mala practica
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    // metodos crud
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

}