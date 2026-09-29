package com.atlas.bank.atlas_bank.service.fee;

import java.math.BigDecimal;

/**
 * 'implements FeeCalculator' indica que esta clase firma un "contrato" con la
 * interfaz FeeCalculator.
 * 
 * Conceptos clave:
 * 1. Obligación de contrato: La clase está obligada a dar una implementación
 * concreta
 * a todos los métodos definidos en la interfaz (supports() y calculate()).
 * 2. Polimorfismo / Patrón Estrategia (Strategy Pattern): Permite tratar
 * distintas calculadoras
 * de comisiones de manera homogénea bajo el tipo común 'FeeCalculator',
 * facilitando añadir
 * nuevos tipos de cuentas en el futuro sin modificar la lógica principal de
 * transferencias.
 */
public class ChekingFeeCalculator implements FeeCalculator {

    @Override
    public boolean supports(String accountType) {

        return "CHECKING".equals(accountType);
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {

        return amount.multiply(new BigDecimal("0.01"));
    }

}
