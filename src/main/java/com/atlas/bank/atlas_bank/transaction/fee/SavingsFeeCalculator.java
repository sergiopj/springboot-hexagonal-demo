// Paquete donde vive la clase. Es como la carpeta / dirección postal del proyecto.
package com.atlas.bank.atlas_bank.transaction.fee;

// Importamos BigDecimal porque para dinero nunca se usa double. 
// double tiene errores de decimales, BigDecimal es exacto.
import java.math.BigDecimal;

// Importamos la anotación que le dice a Spring que gestione esta clase.
import org.springframework.stereotype.Component;

// Le decimos a Spring: "crea tú un objeto de esta clase y guárdalo en tu cajita".
// Así no tenemos que hacer new SavingsFeeCalculator() nunca.
@Component
// Creamos la clase y decimos que FIRMA el contrato FeeCalculator.
// Está obligada a tener los métodos que diga ese contrato (supports y
// calculate).
public class SavingsFeeCalculator implements FeeCalculator {

    // Aviso al compilador: este método viene del contrato FeeCalculator.
    // Si lo escribo mal, que me avise y no compile.
    // Este método pregunta: ¿tú sabes calcular comisión para este tipo de cuenta?
    @Override
    public boolean supports(String accountType) {
        // Si accountType vale "SAVINGS" devuelve true. Si vale otra cosa devuelve
        // false.
        // Se pone "SAVINGS".equals(...) al revés a propósito:
        // Si accountType viene null, así no peta. Si fuera al revés daría
        // NullPointerException.
        return "SAVINGS".equals(accountType);
    }

    // Otra vez, implemento un método que me obliga el contrato.
    // Este método es el que calcula la comisión de verdad.
    @Override
    public BigDecimal calculate(BigDecimal amount) {
        // Multiplicamos el importe por 0.01, o sea, 1% de comisión para ahorro.
        // Ej: amount 100 -> devuelve 1.
        return amount.multiply(new BigDecimal("0.01"));
    }
}