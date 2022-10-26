package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.domain.Interfacing;
import co.com.ies.smol.service.dto.EstablishmentDTO;
import co.com.ies.smol.service.dto.InterfacingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Interfacing} and its DTO {@link InterfacingDTO}.
 */
@Mapper(componentModel = "spring")
public interface InterfacingMapper extends EntityMapper<InterfacingDTO, Interfacing> {
    @Mapping(target = "idEstablishment", source = "idEstablishment", qualifiedByName = "establishmentId")
    InterfacingDTO toDto(Interfacing s);

    @Named("establishmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EstablishmentDTO toDtoEstablishmentId(Establishment establishment);
}
