package co.com.ies.smol.service.criteria;

import co.com.ies.smol.domain.enumeration.EstablishmentType;
import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.Establishment} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.EstablishmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /establishments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EstablishmentCriteria implements Serializable, Criteria {

    /**
     * Class for filtering EstablishmentType
     */
    public static class EstablishmentTypeFilter extends Filter<EstablishmentType> {

        public EstablishmentTypeFilter() {}

        public EstablishmentTypeFilter(EstablishmentTypeFilter filter) {
            super(filter);
        }

        @Override
        public EstablishmentTypeFilter copy() {
            return new EstablishmentTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter liquidationTime;

    private StringFilter name;

    private EstablishmentTypeFilter type;

    private StringFilter municipalityCode;

    private StringFilter neighborhood;

    private StringFilter address;

    private StringFilter coljuegosCode;

    private ZonedDateTimeFilter closeTime;

    private ZonedDateTimeFilter startTime;

    private StringFilter activityType;

    private FloatFilter longitude;

    private FloatFilter latitude;

    private StringFilter mercantileRegistration;

    private LongFilter idOperatorId;

    private Boolean distinct;

    public EstablishmentCriteria() {}

    public EstablishmentCriteria(EstablishmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.liquidationTime = other.liquidationTime == null ? null : other.liquidationTime.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.municipalityCode = other.municipalityCode == null ? null : other.municipalityCode.copy();
        this.neighborhood = other.neighborhood == null ? null : other.neighborhood.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.coljuegosCode = other.coljuegosCode == null ? null : other.coljuegosCode.copy();
        this.closeTime = other.closeTime == null ? null : other.closeTime.copy();
        this.startTime = other.startTime == null ? null : other.startTime.copy();
        this.activityType = other.activityType == null ? null : other.activityType.copy();
        this.longitude = other.longitude == null ? null : other.longitude.copy();
        this.latitude = other.latitude == null ? null : other.latitude.copy();
        this.mercantileRegistration = other.mercantileRegistration == null ? null : other.mercantileRegistration.copy();
        this.idOperatorId = other.idOperatorId == null ? null : other.idOperatorId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EstablishmentCriteria copy() {
        return new EstablishmentCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getLiquidationTime() {
        return liquidationTime;
    }

    public ZonedDateTimeFilter liquidationTime() {
        if (liquidationTime == null) {
            liquidationTime = new ZonedDateTimeFilter();
        }
        return liquidationTime;
    }

    public void setLiquidationTime(ZonedDateTimeFilter liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public EstablishmentTypeFilter getType() {
        return type;
    }

    public EstablishmentTypeFilter type() {
        if (type == null) {
            type = new EstablishmentTypeFilter();
        }
        return type;
    }

    public void setType(EstablishmentTypeFilter type) {
        this.type = type;
    }

    public StringFilter getMunicipalityCode() {
        return municipalityCode;
    }

    public StringFilter municipalityCode() {
        if (municipalityCode == null) {
            municipalityCode = new StringFilter();
        }
        return municipalityCode;
    }

    public void setMunicipalityCode(StringFilter municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public StringFilter getNeighborhood() {
        return neighborhood;
    }

    public StringFilter neighborhood() {
        if (neighborhood == null) {
            neighborhood = new StringFilter();
        }
        return neighborhood;
    }

    public void setNeighborhood(StringFilter neighborhood) {
        this.neighborhood = neighborhood;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getColjuegosCode() {
        return coljuegosCode;
    }

    public StringFilter coljuegosCode() {
        if (coljuegosCode == null) {
            coljuegosCode = new StringFilter();
        }
        return coljuegosCode;
    }

    public void setColjuegosCode(StringFilter coljuegosCode) {
        this.coljuegosCode = coljuegosCode;
    }

    public ZonedDateTimeFilter getCloseTime() {
        return closeTime;
    }

    public ZonedDateTimeFilter closeTime() {
        if (closeTime == null) {
            closeTime = new ZonedDateTimeFilter();
        }
        return closeTime;
    }

    public void setCloseTime(ZonedDateTimeFilter closeTime) {
        this.closeTime = closeTime;
    }

    public ZonedDateTimeFilter getStartTime() {
        return startTime;
    }

    public ZonedDateTimeFilter startTime() {
        if (startTime == null) {
            startTime = new ZonedDateTimeFilter();
        }
        return startTime;
    }

    public void setStartTime(ZonedDateTimeFilter startTime) {
        this.startTime = startTime;
    }

    public StringFilter getActivityType() {
        return activityType;
    }

    public StringFilter activityType() {
        if (activityType == null) {
            activityType = new StringFilter();
        }
        return activityType;
    }

    public void setActivityType(StringFilter activityType) {
        this.activityType = activityType;
    }

    public FloatFilter getLongitude() {
        return longitude;
    }

    public FloatFilter longitude() {
        if (longitude == null) {
            longitude = new FloatFilter();
        }
        return longitude;
    }

    public void setLongitude(FloatFilter longitude) {
        this.longitude = longitude;
    }

    public FloatFilter getLatitude() {
        return latitude;
    }

    public FloatFilter latitude() {
        if (latitude == null) {
            latitude = new FloatFilter();
        }
        return latitude;
    }

    public void setLatitude(FloatFilter latitude) {
        this.latitude = latitude;
    }

    public StringFilter getMercantileRegistration() {
        return mercantileRegistration;
    }

    public StringFilter mercantileRegistration() {
        if (mercantileRegistration == null) {
            mercantileRegistration = new StringFilter();
        }
        return mercantileRegistration;
    }

    public void setMercantileRegistration(StringFilter mercantileRegistration) {
        this.mercantileRegistration = mercantileRegistration;
    }

    public LongFilter getIdOperatorId() {
        return idOperatorId;
    }

    public LongFilter idOperatorId() {
        if (idOperatorId == null) {
            idOperatorId = new LongFilter();
        }
        return idOperatorId;
    }

    public void setIdOperatorId(LongFilter idOperatorId) {
        this.idOperatorId = idOperatorId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EstablishmentCriteria that = (EstablishmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(liquidationTime, that.liquidationTime) &&
            Objects.equals(name, that.name) &&
            Objects.equals(type, that.type) &&
            Objects.equals(municipalityCode, that.municipalityCode) &&
            Objects.equals(neighborhood, that.neighborhood) &&
            Objects.equals(address, that.address) &&
            Objects.equals(coljuegosCode, that.coljuegosCode) &&
            Objects.equals(closeTime, that.closeTime) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(activityType, that.activityType) &&
            Objects.equals(longitude, that.longitude) &&
            Objects.equals(latitude, that.latitude) &&
            Objects.equals(mercantileRegistration, that.mercantileRegistration) &&
            Objects.equals(idOperatorId, that.idOperatorId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            liquidationTime,
            name,
            type,
            municipalityCode,
            neighborhood,
            address,
            coljuegosCode,
            closeTime,
            startTime,
            activityType,
            longitude,
            latitude,
            mercantileRegistration,
            idOperatorId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstablishmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (liquidationTime != null ? "liquidationTime=" + liquidationTime + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (municipalityCode != null ? "municipalityCode=" + municipalityCode + ", " : "") +
            (neighborhood != null ? "neighborhood=" + neighborhood + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (coljuegosCode != null ? "coljuegosCode=" + coljuegosCode + ", " : "") +
            (closeTime != null ? "closeTime=" + closeTime + ", " : "") +
            (startTime != null ? "startTime=" + startTime + ", " : "") +
            (activityType != null ? "activityType=" + activityType + ", " : "") +
            (longitude != null ? "longitude=" + longitude + ", " : "") +
            (latitude != null ? "latitude=" + latitude + ", " : "") +
            (mercantileRegistration != null ? "mercantileRegistration=" + mercantileRegistration + ", " : "") +
            (idOperatorId != null ? "idOperatorId=" + idOperatorId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
