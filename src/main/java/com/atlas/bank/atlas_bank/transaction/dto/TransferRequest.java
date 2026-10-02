package com.atlas.bank.atlas_bank.transaction.dto;

import java.math.BigDecimal;

import lombok.Data;

// Anotación de Lombok que genera automáticamente en segundo plano:
// getters, setters, toString, equals, hashCode y el constructor para campos requeridos.
@Data
public class TransferRequest {
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
}
