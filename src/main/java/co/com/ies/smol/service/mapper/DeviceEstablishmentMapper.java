package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.DeviceEstablishment;
import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.service.dto.DeviceEstablishmentDTO;
import co.com.ies.smol.service.dto.EstablishmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DeviceEstablishment} and its DTO {@link DeviceEstablishmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeviceEstablishmentMapper extends EntityMapper<DeviceEstablishmentDTO, DeviceEstablishment> {
    @Mapping(target = "idEstablishment", source = "idEstablishment", qualifiedByName = "establishmentId")
    DeviceEstablishmentDTO toDto(DeviceEstablishment s);

    @Named("establishmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EstablishmentDTO toDtoEstablishmentId(Establishment establishment);
}
