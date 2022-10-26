package co.com.ies.smol.service.dto;

import co.com.ies.smol.domain.enumeration.EstablishmentType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link co.com.ies.smol.domain.Establishment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EstablishmentDTO implements Serializable {

    private Long id;

    private ZonedDateTime liquidationTime;

    private String name;

    private EstablishmentType type;

    private String municipalityCode;

    private String neighborhood;

    private String address;

    private String coljuegosCode;

    private ZonedDateTime closeTime;

    private ZonedDateTime startTime;

    private String activityType;

    private Float longitude;

    private Float latitude;

    private String mercantileRegistration;

    private OperatorDTO idOperator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getLiquidationTime() {
        return liquidationTime;
    }

    public void setLiquidationTime(ZonedDateTime liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EstablishmentType getType() {
        return type;
    }

    public void setType(EstablishmentType type) {
        this.type = type;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColjuegosCode() {
        return coljuegosCode;
    }

    public void setColjuegosCode(String coljuegosCode) {
        this.coljuegosCode = coljuegosCode;
    }

    public ZonedDateTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getMercantileRegistration() {
        return mercantileRegistration;
    }

    public void setMercantileRegistration(String mercantileRegistration) {
        this.mercantileRegistration = mercantileRegistration;
    }

    public OperatorDTO getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(OperatorDTO idOperator) {
        this.idOperator = idOperator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstablishmentDTO)) {
            return false;
        }

        EstablishmentDTO establishmentDTO = (EstablishmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, establishmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstablishmentDTO{" +
            "id=" + getId() +
            ", liquidationTime='" + getLiquidationTime() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", municipalityCode='" + getMunicipalityCode() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", address='" + getAddress() + "'" +
            ", coljuegosCode='" + getColjuegosCode() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", activityType='" + getActivityType() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", mercantileRegistration='" + getMercantileRegistration() + "'" +
            ", idOperator=" + getIdOperator() +
            "}";
    }
}
