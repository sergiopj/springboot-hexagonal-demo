package com.atlas.bank.atlas_bank.account.service;

import java.util.List;

import com.atlas.bank.atlas_bank.account.model.Account;

public interface IAccountService {

    public Account create(Account account);

    public List<Account> findAll();

    public Account findById(Long id);

}
