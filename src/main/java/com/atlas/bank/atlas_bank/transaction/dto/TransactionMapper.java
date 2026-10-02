package com.atlas.bank.atlas_bank.transaction.dto;

import org.mapstruct.Mapper;

import com.atlas.bank.atlas_bank.transaction.model.Transaction;

// MapStruct genera automáticamente la implementación en tiempo de compilación.
// Al coincidir exactamente los nombres y tipos de atributos entre Transaction y TransactionResponse,
// el mapeo es 100% automático y no requiere anotaciones @Mapping manuales.
// componentModel = "spring" registra el mapper como un Bean de Spring para poder inyectarlo.
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    // Mapeo automático de Transaction a TransactionResponse:
    // Lee los getters de la entidad y los enlaza con el DTO sin necesidad de código manual.
    TransactionResponse toResponse(Transaction transaction);

}
