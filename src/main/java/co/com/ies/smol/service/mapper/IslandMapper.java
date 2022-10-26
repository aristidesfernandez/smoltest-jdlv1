package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Island;
import co.com.ies.smol.service.dto.IslandDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Island} and its DTO {@link IslandDTO}.
 */
@Mapper(componentModel = "spring")
public interface IslandMapper extends EntityMapper<IslandDTO, Island> {}
