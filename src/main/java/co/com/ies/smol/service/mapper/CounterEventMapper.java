package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.CounterEvent;
import co.com.ies.smol.domain.CounterType;
import co.com.ies.smol.domain.EventDevice;
import co.com.ies.smol.service.dto.CounterEventDTO;
import co.com.ies.smol.service.dto.CounterTypeDTO;
import co.com.ies.smol.service.dto.EventDeviceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CounterEvent} and its DTO {@link CounterEventDTO}.
 */
@Mapper(componentModel = "spring")
public interface CounterEventMapper extends EntityMapper<CounterEventDTO, CounterEvent> {
    @Mapping(target = "counter", source = "counter", qualifiedByName = "counterTypeCounterCode")
    @Mapping(target = "eventDevice", source = "eventDevice", qualifiedByName = "eventDeviceId")
    CounterEventDTO toDto(CounterEvent s);

    @Named("counterTypeCounterCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "counterCode", source = "counterCode")
    CounterTypeDTO toDtoCounterTypeCounterCode(CounterType counterType);

    @Named("eventDeviceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EventDeviceDTO toDtoEventDeviceId(EventDevice eventDevice);
}