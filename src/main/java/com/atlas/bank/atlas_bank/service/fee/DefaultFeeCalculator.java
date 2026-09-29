package com.atlas.bank.atlas_bank.service.fee;

import java.math.BigDecimal;

/**
 * Esta clase es la estrategia POR DEFECTO (o plan de respaldo / fallback) para
 * calcular comisiones.
 * 
 * ¿Qué hace en el contexto de nuestra app?
 * 1. supports(accountType) devuelve siempre 'true': Significa que acepta
 * CUALQUIER tipo de cuenta.
 * Actúa como un comodín cuando ninguna otra calculadora específica (como
 * Savings o Checking)
 * reconoce el tipo de cuenta.
 * 2. calculate(amount) devuelve 'BigDecimal.ZERO': Para cualquier tipo de
 * cuenta no contemplado,
 * la comisión cobrada es de 0.
 * 
 * En patrones de diseño se conoce como "Default Strategy" o "Fallback": asegura
 * que el sistema
 * nunca falle ni lance un error si llega un tipo de cuenta inesperado,
 * aplicando comisión cero.
 */
public class DefaultFeeCalculator implements FeeCalculator {

    @Override
    public boolean supports(String accountType) {

        return true;
    }

    @Override
    public BigDecimal calculate(BigDecimal amount) {

        return BigDecimal.ZERO;
    }

}
