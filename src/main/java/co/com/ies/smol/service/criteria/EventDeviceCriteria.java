package co.com.ies.smol.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.EventDevice} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.EventDeviceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /event-devices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EventDeviceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter createdAt;

    private BooleanFilter theoreticalPercentage;

    private DoubleFilter moneyDenomination;

    private LongFilter idEstablishmentId;

    private LongFilter idEventTypeId;

    private Boolean distinct;

    public EventDeviceCriteria() {}

    public EventDeviceCriteria(EventDeviceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.createdAt = other.createdAt == null ? null : other.createdAt.copy();
        this.theoreticalPercentage = other.theoreticalPercentage == null ? null : other.theoreticalPercentage.copy();
        this.moneyDenomination = other.moneyDenomination == null ? null : other.moneyDenomination.copy();
        this.idEstablishmentId = other.idEstablishmentId == null ? null : other.idEstablishmentId.copy();
        this.idEventTypeId = other.idEventTypeId == null ? null : other.idEventTypeId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EventDeviceCriteria copy() {
        return new EventDeviceCriteria(this);
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

    public ZonedDateTimeFilter getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTimeFilter createdAt() {
        if (createdAt == null) {
            createdAt = new ZonedDateTimeFilter();
        }
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTimeFilter createdAt) {
        this.createdAt = createdAt;
    }

    public BooleanFilter getTheoreticalPercentage() {
        return theoreticalPercentage;
    }

    public BooleanFilter theoreticalPercentage() {
        if (theoreticalPercentage == null) {
            theoreticalPercentage = new BooleanFilter();
        }
        return theoreticalPercentage;
    }

    public void setTheoreticalPercentage(BooleanFilter theoreticalPercentage) {
        this.theoreticalPercentage = theoreticalPercentage;
    }

    public DoubleFilter getMoneyDenomination() {
        return moneyDenomination;
    }

    public DoubleFilter moneyDenomination() {
        if (moneyDenomination == null) {
            moneyDenomination = new DoubleFilter();
        }
        return moneyDenomination;
    }

    public void setMoneyDenomination(DoubleFilter moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }

    public LongFilter getIdEstablishmentId() {
        return idEstablishmentId;
    }

    public LongFilter idEstablishmentId() {
        if (idEstablishmentId == null) {
            idEstablishmentId = new LongFilter();
        }
        return idEstablishmentId;
    }

    public void setIdEstablishmentId(LongFilter idEstablishmentId) {
        this.idEstablishmentId = idEstablishmentId;
    }

    public LongFilter getIdEventTypeId() {
        return idEventTypeId;
    }

    public LongFilter idEventTypeId() {
        if (idEventTypeId == null) {
            idEventTypeId = new LongFilter();
        }
        return idEventTypeId;
    }

    public void setIdEventTypeId(LongFilter idEventTypeId) {
        this.idEventTypeId = idEventTypeId;
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
        final EventDeviceCriteria that = (EventDeviceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(theoreticalPercentage, that.theoreticalPercentage) &&
            Objects.equals(moneyDenomination, that.moneyDenomination) &&
            Objects.equals(idEstablishmentId, that.idEstablishmentId) &&
            Objects.equals(idEventTypeId, that.idEventTypeId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, theoreticalPercentage, moneyDenomination, idEstablishmentId, idEventTypeId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventDeviceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (createdAt != null ? "createdAt=" + createdAt + ", " : "") +
            (theoreticalPercentage != null ? "theoreticalPercentage=" + theoreticalPercentage + ", " : "") +
            (moneyDenomination != null ? "moneyDenomination=" + moneyDenomination + ", " : "") +
            (idEstablishmentId != null ? "idEstablishmentId=" + idEstablishmentId + ", " : "") +
            (idEventTypeId != null ? "idEventTypeId=" + idEventTypeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
