package com.atlas.bank.atlas_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlas.bank.atlas_bank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
