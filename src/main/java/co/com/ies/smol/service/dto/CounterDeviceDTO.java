package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.com.ies.smol.domain.CounterDevice} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CounterDeviceDTO implements Serializable {

    private UUID id;

    private BigDecimal value;

    private BigDecimal rolloverValue;

    private BigDecimal creditSale;

    private Boolean manualCounter;

    private BigDecimal manualMultiplier;

    private Boolean decimalsManualCounter;

    private CounterTypeDTO counterCode;

    private DeviceDTO idDevice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getRolloverValue() {
        return rolloverValue;
    }

    public void setRolloverValue(BigDecimal rolloverValue) {
        this.rolloverValue = rolloverValue;
    }

    public BigDecimal getCreditSale() {
        return creditSale;
    }

    public void setCreditSale(BigDecimal creditSale) {
        this.creditSale = creditSale;
    }

    public Boolean getManualCounter() {
        return manualCounter;
    }

    public void setManualCounter(Boolean manualCounter) {
        this.manualCounter = manualCounter;
    }

    public BigDecimal getManualMultiplier() {
        return manualMultiplier;
    }

    public void setManualMultiplier(BigDecimal manualMultiplier) {
        this.manualMultiplier = manualMultiplier;
    }

    public Boolean getDecimalsManualCounter() {
        return decimalsManualCounter;
    }

    public void setDecimalsManualCounter(Boolean decimalsManualCounter) {
        this.decimalsManualCounter = decimalsManualCounter;
    }

    public CounterTypeDTO getCounterCode() {
        return counterCode;
    }

    public void setCounterCode(CounterTypeDTO counterCode) {
        this.counterCode = counterCode;
    }

    public DeviceDTO getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(DeviceDTO idDevice) {
        this.idDevice = idDevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CounterDeviceDTO)) {
            return false;
        }

        CounterDeviceDTO counterDeviceDTO = (CounterDeviceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, counterDeviceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CounterDeviceDTO{" +
            "id='" + getId() + "'" +
            ", value=" + getValue() +
            ", rolloverValue=" + getRolloverValue() +
            ", creditSale=" + getCreditSale() +
            ", manualCounter='" + getManualCounter() + "'" +
            ", manualMultiplier=" + getManualMultiplier() +
            ", decimalsManualCounter='" + getDecimalsManualCounter() + "'" +
            ", counterCode=" + getCounterCode() +
            ", idDevice=" + getIdDevice() +
            "}";
    }
}
