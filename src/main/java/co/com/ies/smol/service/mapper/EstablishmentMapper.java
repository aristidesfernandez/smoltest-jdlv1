package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.domain.Operator;
import co.com.ies.smol.service.dto.EstablishmentDTO;
import co.com.ies.smol.service.dto.OperatorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Establishment} and its DTO {@link EstablishmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface EstablishmentMapper extends EntityMapper<EstablishmentDTO, Establishment> {
    @Mapping(target = "idOperator", source = "idOperator", qualifiedByName = "operatorId")
    EstablishmentDTO toDto(Establishment s);

    @Named("operatorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OperatorDTO toDtoOperatorId(Operator operator);
}
