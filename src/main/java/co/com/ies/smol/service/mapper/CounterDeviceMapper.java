package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.CounterDevice;
import co.com.ies.smol.domain.CounterType;
import co.com.ies.smol.domain.Device;
import co.com.ies.smol.service.dto.CounterDeviceDTO;
import co.com.ies.smol.service.dto.CounterTypeDTO;
import co.com.ies.smol.service.dto.DeviceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CounterDevice} and its DTO {@link CounterDeviceDTO}.
 */
@Mapper(componentModel = "spring")
public interface CounterDeviceMapper extends EntityMapper<CounterDeviceDTO, CounterDevice> {
    @Mapping(target = "counterCode", source = "counterCode", qualifiedByName = "counterTypeId")
    @Mapping(target = "idDevice", source = "idDevice", qualifiedByName = "deviceId")
    CounterDeviceDTO toDto(CounterDevice s);

    @Named("counterTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CounterTypeDTO toDtoCounterTypeId(CounterType counterType);

    @Named("deviceId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DeviceDTO toDtoDeviceId(Device device);
}
