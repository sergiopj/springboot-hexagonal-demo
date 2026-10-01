package com.atlas.bank.atlas_bank.account.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// Anotación de Lombok que genera automáticamente en segundo plano:
// getters, setters, toString, equals, hashCode y el constructor para campos requeridos.
@Data
// Permite construir objetos con el patrón Builder: AccountResponse.builder().campo(valor).build()
@Builder
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; // enum SAVING, CHECKING
    private String status; // enum ACTIVE CLOSED FROZEN
    private BigDecimal balance;
    private LocalDateTime createAt;
}
