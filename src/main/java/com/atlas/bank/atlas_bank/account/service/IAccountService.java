package com.atlas.bank.atlas_bank.account.service;

import java.util.List;

import com.atlas.bank.atlas_bank.account.dto.AccountResponse;
import com.atlas.bank.atlas_bank.account.dto.CreateAccountRequest;

public interface IAccountService {

    AccountResponse create(CreateAccountRequest request);

    List<AccountResponse> findAll();

    AccountResponse findById(Long id);

}
