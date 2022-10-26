package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Department;
import co.com.ies.smol.domain.Municipality;
import co.com.ies.smol.service.dto.DepartmentDTO;
import co.com.ies.smol.service.dto.MunicipalityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Municipality} and its DTO {@link MunicipalityDTO}.
 */
@Mapper(componentModel = "spring")
public interface MunicipalityMapper extends EntityMapper<MunicipalityDTO, Municipality> {
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentId")
    MunicipalityDTO toDto(Municipality s);

    @Named("departmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartmentDTO toDtoDepartmentId(Department department);
}
