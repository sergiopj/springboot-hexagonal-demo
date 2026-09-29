package com.atlas.bank.atlas_bank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atlas.bank.atlas_bank.model.Account;
import com.atlas.bank.atlas_bank.model.Transaction;
import com.atlas.bank.atlas_bank.repository.AccountRepository;
import com.atlas.bank.atlas_bank.repository.TransactionRepository;
import com.atlas.bank.atlas_bank.service.fee.FeeCalculator;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    /**
     * MAGIA DE SPRING (Inyección de dependencias en lista / Patrón Estrategia):
     * 
     * Al inyectar 'List<FeeCalculator>', Spring busca automáticamente en todo el
     * proyecto
     * todas las clases anotadas con @Component que firmen el contrato 'implements
     * FeeCalculator'
     * (SavingsFeeCalculator, CheckingFeeCalculator, DefaultFeeCalculator...) y las
     * mete
     * en esta lista.
     * 
     * ¿Por qué es tan potente?
     * 1. Cumple el principio Open/Closed (SOLID): Si mañana creamos una
     * 'InvestmentFeeCalculator', es ampliable y solido
     * solo creamos la clase con @Component y ya funciona; NO hace falta modificar
     * esta clase TransferService.
     * 2. Desacoplamiento: TransferService no sabe ni le importa cuántas
     * calculadoras existen;
     * simplemente las recorre con un bucle o stream buscando cuál 'supports()' el
     * tipo de cuenta.
     */
    private final List<FeeCalculator> feeCalculators;

    // transaccional es una unica transaccion a bd o se hace todo o nada y sale
    // error
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount) {
        // Buscar cuentas
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        // Validar que la cuenta esté activa
        if (!"ACTIVE".equals(from.getStatus())) {
            throw new RuntimeException("La cuenta origen no está activa");
        }
        if (!"ACTIVE".equals(to.getStatus())) {
            throw new RuntimeException("La cuenta destino no está activa");
        }

        // Validar fondos
        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Fondos insuficientes");
        }

        // Calcular comisión — hardcodeada
        BigDecimal fee = // 1. Abrimos una "cinta transportadora" (Stream) a partir de la lista.
                // En lugar de hacer un bucle 'for' tradicional, el Stream permite procesar
                // elementos uno a uno.
                feeCalculators.stream()

                        // 2. Colador / Filtro: a cada calculadora ('fc') le preguntamos:
                        // ¿tú sabes calcular la comisión para el tipo de cuenta de origen (ej:
                        // "SAVINGS", "CHECKING")?
                        // Solo deja pasar a las que devuelvan 'true' en su método supports().
                        .filter(fc -> fc.supports(from.getType()))

                        // 3. De todas las que hayan pasado el filtro, quédate solo con la primera y
                        // para de buscar.
                        // Devuelve un 'Optional': una caja que puede contener la calculadora encontrada
                        // o estar vacía.
                        .findFirst()

                        // 4. Abrimos la caja del Optional:
                        // - Si la caja tiene una calculadora, la saca y continúa.
                        // - Si la caja está vacía (ninguna calculadora servía para ese tipo), detiene
                        // todo y lanza una excepción.
                        .orElseThrow(() -> new RuntimeException("No hay calculador para el tipo " + from.getType()))

                        // 5. Con la calculadora ya encontrada y segura en mano, ejecutamos su método
                        // calculate()
                        // pasándole el importe de la transferencia para que devuelva la comisión exacta
                        // (BigDecimal).
                        .calculate(amount);

        // Actualizar saldos
        from.setBalance(from.getBalance().subtract(amount).subtract(fee));
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(from);
        accountRepository.save(to);

        // Crear transacción
        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(fromId);
        transaction.setTargetAccountId(toId);
        transaction.setAmount(amount);
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");

        return transactionRepository.save(transaction);
    }

}
