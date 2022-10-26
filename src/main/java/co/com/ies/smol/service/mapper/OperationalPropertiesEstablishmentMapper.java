package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.domain.OperationalPropertiesEstablishment;
import co.com.ies.smol.service.dto.EstablishmentDTO;
import co.com.ies.smol.service.dto.OperationalPropertiesEstablishmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperationalPropertiesEstablishment} and its DTO {@link OperationalPropertiesEstablishmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperationalPropertiesEstablishmentMapper
    extends EntityMapper<OperationalPropertiesEstablishmentDTO, OperationalPropertiesEstablishment> {
    @Mapping(target = "establishment", source = "establishment", qualifiedByName = "establishmentId")
    OperationalPropertiesEstablishmentDTO toDto(OperationalPropertiesEstablishment s);

    @Named("establishmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EstablishmentDTO toDtoEstablishmentId(Establishment establishment);
}
