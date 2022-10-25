package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.com.ies.smol.domain.DeviceEstablishment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeviceEstablishmentDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime registrationAt;

    @NotNull
    private String serial;

    private ZonedDateTime departureAt;

    private Integer deviceNumber;

    private Integer consecutiveDevice;

    private Float negativeAward;

    private EstablishmentDTO idEstablishment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRegistrationAt() {
        return registrationAt;
    }

    public void setRegistrationAt(ZonedDateTime registrationAt) {
        this.registrationAt = registrationAt;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public ZonedDateTime getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(ZonedDateTime departureAt) {
        this.departureAt = departureAt;
    }

    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Integer getConsecutiveDevice() {
        return consecutiveDevice;
    }

    public void setConsecutiveDevice(Integer consecutiveDevice) {
        this.consecutiveDevice = consecutiveDevice;
    }

    public Float getNegativeAward() {
        return negativeAward;
    }

    public void setNegativeAward(Float negativeAward) {
        this.negativeAward = negativeAward;
    }

    public EstablishmentDTO getIdEstablishment() {
        return idEstablishment;
    }

    public void setIdEstablishment(EstablishmentDTO idEstablishment) {
        this.idEstablishment = idEstablishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceEstablishmentDTO)) {
            return false;
        }

        DeviceEstablishmentDTO deviceEstablishmentDTO = (DeviceEstablishmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deviceEstablishmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceEstablishmentDTO{" +
            "id=" + getId() +
            ", registrationAt='" + getRegistrationAt() + "'" +
            ", serial='" + getSerial() + "'" +
            ", departureAt='" + getDepartureAt() + "'" +
            ", deviceNumber=" + getDeviceNumber() +
            ", consecutiveDevice=" + getConsecutiveDevice() +
            ", negativeAward=" + getNegativeAward() +
            ", idEstablishment=" + getIdEstablishment() +
            "}";
    }
}
