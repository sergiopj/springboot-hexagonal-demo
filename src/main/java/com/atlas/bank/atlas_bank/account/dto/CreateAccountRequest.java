package com.atlas.bank.atlas_bank.account.dto;

import java.math.BigDecimal;

import lombok.Data;

// DTO (Data Transfer Object) para la creación de cuentas. Contiene los campos necesarios para crear una nueva cuenta bancaria.
// Anotación de Lombok que genera automáticamente en segundo plano:
// getters, setters, toString, equals, hashCode y el constructor para campos requeridos.
@Data
public class CreateAccountRequest {

    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; // enum SAVING, CHECKING
    private BigDecimal balance;

}
