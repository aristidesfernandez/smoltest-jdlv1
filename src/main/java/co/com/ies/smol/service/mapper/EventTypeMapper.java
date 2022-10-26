package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.EventType;
import co.com.ies.smol.domain.Model;
import co.com.ies.smol.service.dto.EventTypeDTO;
import co.com.ies.smol.service.dto.ModelDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventType} and its DTO {@link EventTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EventTypeMapper extends EntityMapper<EventTypeDTO, EventType> {
    @Mapping(target = "idEventTypes", source = "idEventTypes", qualifiedByName = "modelIdSet")
    EventTypeDTO toDto(EventType s);

    @Mapping(target = "removeIdEventType", ignore = true)
    EventType toEntity(EventTypeDTO eventTypeDTO);

    @Named("modelId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ModelDTO toDtoModelId(Model model);

    @Named("modelIdSet")
    default Set<ModelDTO> toDtoModelIdSet(Set<Model> model) {
        return model.stream().map(this::toDtoModelId).collect(Collectors.toSet());
    }
}
