package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Operator;
import co.com.ies.smol.service.dto.OperatorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Operator} and its DTO {@link OperatorDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperatorMapper extends EntityMapper<OperatorDTO, Operator> {}
