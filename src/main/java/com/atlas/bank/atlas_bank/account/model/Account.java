package com.atlas.bank.atlas_bank.account.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Solo incluye en equals() y hashCode() los campos marcados explícitamente con @EqualsAndHashCode.Include
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account {

    @Id // Clave primaria (Primary Key) de la tabla en base de datos
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental generado automáticamente por la BD
    @EqualsAndHashCode.Include // Dos cuentas se consideran la misma si comparten el mismo ID
    private Long id;
    private String accountNumber;
    private String ownerName;
    private String email;
    private String type; // enum SAVING, CHECKING
    private String status; // enum ACTIVE CLOSED FROZEN
    private BigDecimal balance;
    private LocalDateTime createAt;

    /**
     * Callback del ciclo de vida de JPA.
     * Se ejecuta automáticamente justo antes del primer INSERT (@PrePersist).
     * Garantiza valores por defecto para evitar nulos y estados inconsistentes
     * si el cliente no los proporciona al crear la cuenta.
     */
    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        if (status == null) {
            status = "ACTIVE";
        }
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
    }

}
