package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.domain.EventDevice;
import co.com.ies.smol.domain.EventType;
import co.com.ies.smol.service.dto.EstablishmentDTO;
import co.com.ies.smol.service.dto.EventDeviceDTO;
import co.com.ies.smol.service.dto.EventTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EventDevice} and its DTO {@link EventDeviceDTO}.
 */
@Mapper(componentModel = "spring")
public interface EventDeviceMapper extends EntityMapper<EventDeviceDTO, EventDevice> {
    @Mapping(target = "idEstablishment", source = "idEstablishment", qualifiedByName = "establishmentId")
    @Mapping(target = "idEventType", source = "idEventType", qualifiedByName = "eventTypeId")
    EventDeviceDTO toDto(EventDevice s);

    @Named("establishmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EstablishmentDTO toDtoEstablishmentId(Establishment establishment);

    @Named("eventTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EventTypeDTO toDtoEventTypeId(EventType eventType);
}
