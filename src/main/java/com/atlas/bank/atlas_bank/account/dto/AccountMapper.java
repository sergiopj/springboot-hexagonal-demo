package com.atlas.bank.atlas_bank.account.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.atlas.bank.atlas_bank.account.model.Account;

// MapStruct genera automáticamente el código de mapeo en tiempo de compilación.
// Si los nombres y tipos de atributos coinciden entre origen y destino, los enlaza de forma automática.
// componentModel = "spring" convierte esta interfaz en un componente/Bean inyectable de Spring.
@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Convierte CreateAccountRequest a la entidad Account.
    // Los campos que coinciden se asignan solos. Se usa ignore = true para aquellos
    // que no vienen en la petición y son administrados por el sistema (BD o lógica de negocio).
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Account toEntity(CreateAccountRequest request);

    // Mapeo 100% automático: como los campos de Account coinciden con AccountResponse,
    // MapStruct genera los getters y setters automáticamente sin necesidad de anotaciones @Mapping.
    AccountResponse toResponse(Account account);

}
