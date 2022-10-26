package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.Country;
import co.com.ies.smol.domain.Department;
import co.com.ies.smol.service.dto.CountryDTO;
import co.com.ies.smol.service.dto.DepartmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {
    @Mapping(target = "country", source = "country", qualifiedByName = "countryId")
    DepartmentDTO toDto(Department s);

    @Named("countryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CountryDTO toDtoCountryId(Country country);
}
