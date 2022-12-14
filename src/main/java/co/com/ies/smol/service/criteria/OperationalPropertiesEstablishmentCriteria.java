package co.com.ies.smol.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.OperationalPropertiesEstablishment} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.OperationalPropertiesEstablishmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /operational-properties-establishments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationalPropertiesEstablishmentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter value;

    private IntegerFilter key;

    private LongFilter idEstablishmentId;

    private Boolean distinct;

    public OperationalPropertiesEstablishmentCriteria() {}

    public OperationalPropertiesEstablishmentCriteria(OperationalPropertiesEstablishmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.value = other.value == null ? null : other.value.copy();
        this.key = other.key == null ? null : other.key.copy();
        this.idEstablishmentId = other.idEstablishmentId == null ? null : other.idEstablishmentId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OperationalPropertiesEstablishmentCriteria copy() {
        return new OperationalPropertiesEstablishmentCriteria(this);
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

    public StringFilter getValue() {
        return value;
    }

    public StringFilter value() {
        if (value == null) {
            value = new StringFilter();
        }
        return value;
    }

    public void setValue(StringFilter value) {
        this.value = value;
    }

    public IntegerFilter getKey() {
        return key;
    }

    public IntegerFilter key() {
        if (key == null) {
            key = new IntegerFilter();
        }
        return key;
    }

    public void setKey(IntegerFilter key) {
        this.key = key;
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
        final OperationalPropertiesEstablishmentCriteria that = (OperationalPropertiesEstablishmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(value, that.value) &&
            Objects.equals(key, that.key) &&
            Objects.equals(idEstablishmentId, that.idEstablishmentId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, key, idEstablishmentId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationalPropertiesEstablishmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (key != null ? "key=" + key + ", " : "") +
            (idEstablishmentId != null ? "idEstablishmentId=" + idEstablishmentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
