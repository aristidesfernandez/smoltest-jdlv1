package co.com.ies.smol.service.mapper;

import co.com.ies.smol.domain.CurrencyType;
import co.com.ies.smol.domain.Device;
import co.com.ies.smol.domain.DeviceCategory;
import co.com.ies.smol.domain.DeviceType;
import co.com.ies.smol.domain.Formula;
import co.com.ies.smol.domain.Interfacing;
import co.com.ies.smol.domain.Island;
import co.com.ies.smol.domain.Model;
import co.com.ies.smol.service.dto.CurrencyTypeDTO;
import co.com.ies.smol.service.dto.DeviceCategoryDTO;
import co.com.ies.smol.service.dto.DeviceDTO;
import co.com.ies.smol.service.dto.DeviceTypeDTO;
import co.com.ies.smol.service.dto.FormulaDTO;
import co.com.ies.smol.service.dto.InterfacingDTO;
import co.com.ies.smol.service.dto.IslandDTO;
import co.com.ies.smol.service.dto.ModelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Device} and its DTO {@link DeviceDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeviceMapper extends EntityMapper<DeviceDTO, Device> {
    @Mapping(target = "interfacing", source = "interfacing", qualifiedByName = "interfacingId")
    @Mapping(target = "model", source = "model", qualifiedByName = "modelId")
    @Mapping(target = "deviceCategory", source = "deviceCategory", qualifiedByName = "deviceCategoryId")
    @Mapping(target = "deviceType", source = "deviceType", qualifiedByName = "deviceTypeId")
    @Mapping(target = "formulaHandpay", source = "formulaHandpay", qualifiedByName = "formulaId")
    @Mapping(target = "formulaJackpot", source = "formulaJackpot", qualifiedByName = "formulaId")
    @Mapping(target = "island", source = "island", qualifiedByName = "islandId")
    @Mapping(target = "urrencyType", source = "urrencyType", qualifiedByName = "currencyTypeId")
    DeviceDTO toDto(Device s);

    @Named("interfacingId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    InterfacingDTO toDtoInterfacingId(Interfacing interfacing);

    @Named("modelId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ModelDTO toDtoModelId(Model model);

    @Named("deviceCategoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DeviceCategoryDTO toDtoDeviceCategoryId(DeviceCategory deviceCategory);

    @Named("deviceTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DeviceTypeDTO toDtoDeviceTypeId(DeviceType deviceType);

    @Named("formulaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    FormulaDTO toDtoFormulaId(Formula formula);

    @Named("islandId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    IslandDTO toDtoIslandId(Island island);

    @Named("currencyTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CurrencyTypeDTO toDtoCurrencyTypeId(CurrencyType currencyType);
}
