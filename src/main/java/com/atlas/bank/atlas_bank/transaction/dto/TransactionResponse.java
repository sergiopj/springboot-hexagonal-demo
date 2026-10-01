package com.atlas.bank.atlas_bank.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// Anotación de Lombok que genera automáticamente en segundo plano:
// getters, setters, toString, equals, hashCode y el constructor para campos requeridos.
@Data
// Permite construir objetos con el patrón Builder: TransactionResponse.builder().campo(valor).build()
@Builder
public class TransactionResponse {
    private Long id;
    private String type; // DEPOSIT, WITHDRAWAL, TRANSFER
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private String status; // PENDING, EXECUTED, REJECTED
    private LocalDateTime createdAt;

}
