package com.atlas.bank.atlas_bank.transaction.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.transaction.service.ITransactionQueryService;
import com.atlas.bank.atlas_bank.transaction.service.ITransferService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor

public class TransactionController {

    private final ITransferService iTransferService;
    private final ITransactionQueryService iTransactionQueryService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam BigDecimal amount) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iTransferService.execute(fromId, toId, amount));

    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionList(@PathVariable Long id) {
        return ResponseEntity.ok(iTransactionQueryService.getAccountById(id));
    }

}
