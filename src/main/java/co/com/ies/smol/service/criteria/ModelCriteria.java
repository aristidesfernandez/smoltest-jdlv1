package co.com.ies.smol.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.Model} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.ModelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /models?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ModelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter code;

    private StringFilter name;

    private BooleanFilter subtractBonus;

    private BigDecimalFilter collectionCeil;

    private BigDecimalFilter rolloverLimit;

    private LongFilter idManufacturerId;

    private LongFilter idFormulaId;

    private LongFilter idModelId;

    private Boolean distinct;

    public ModelCriteria() {}

    public ModelCriteria(ModelCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.subtractBonus = other.subtractBonus == null ? null : other.subtractBonus.copy();
        this.collectionCeil = other.collectionCeil == null ? null : other.collectionCeil.copy();
        this.rolloverLimit = other.rolloverLimit == null ? null : other.rolloverLimit.copy();
        this.idManufacturerId = other.idManufacturerId == null ? null : other.idManufacturerId.copy();
        this.idFormulaId = other.idFormulaId == null ? null : other.idFormulaId.copy();
        this.idModelId = other.idModelId == null ? null : other.idModelId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ModelCriteria copy() {
        return new ModelCriteria(this);
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

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public BooleanFilter getSubtractBonus() {
        return subtractBonus;
    }

    public BooleanFilter subtractBonus() {
        if (subtractBonus == null) {
            subtractBonus = new BooleanFilter();
        }
        return subtractBonus;
    }

    public void setSubtractBonus(BooleanFilter subtractBonus) {
        this.subtractBonus = subtractBonus;
    }

    public BigDecimalFilter getCollectionCeil() {
        return collectionCeil;
    }

    public BigDecimalFilter collectionCeil() {
        if (collectionCeil == null) {
            collectionCeil = new BigDecimalFilter();
        }
        return collectionCeil;
    }

    public void setCollectionCeil(BigDecimalFilter collectionCeil) {
        this.collectionCeil = collectionCeil;
    }

    public BigDecimalFilter getRolloverLimit() {
        return rolloverLimit;
    }

    public BigDecimalFilter rolloverLimit() {
        if (rolloverLimit == null) {
            rolloverLimit = new BigDecimalFilter();
        }
        return rolloverLimit;
    }

    public void setRolloverLimit(BigDecimalFilter rolloverLimit) {
        this.rolloverLimit = rolloverLimit;
    }

    public LongFilter getIdManufacturerId() {
        return idManufacturerId;
    }

    public LongFilter idManufacturerId() {
        if (idManufacturerId == null) {
            idManufacturerId = new LongFilter();
        }
        return idManufacturerId;
    }

    public void setIdManufacturerId(LongFilter idManufacturerId) {
        this.idManufacturerId = idManufacturerId;
    }

    public LongFilter getIdFormulaId() {
        return idFormulaId;
    }

    public LongFilter idFormulaId() {
        if (idFormulaId == null) {
            idFormulaId = new LongFilter();
        }
        return idFormulaId;
    }

    public void setIdFormulaId(LongFilter idFormulaId) {
        this.idFormulaId = idFormulaId;
    }

    public LongFilter getIdModelId() {
        return idModelId;
    }

    public LongFilter idModelId() {
        if (idModelId == null) {
            idModelId = new LongFilter();
        }
        return idModelId;
    }

    public void setIdModelId(LongFilter idModelId) {
        this.idModelId = idModelId;
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
        final ModelCriteria that = (ModelCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(code, that.code) &&
            Objects.equals(name, that.name) &&
            Objects.equals(subtractBonus, that.subtractBonus) &&
            Objects.equals(collectionCeil, that.collectionCeil) &&
            Objects.equals(rolloverLimit, that.rolloverLimit) &&
            Objects.equals(idManufacturerId, that.idManufacturerId) &&
            Objects.equals(idFormulaId, that.idFormulaId) &&
            Objects.equals(idModelId, that.idModelId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            code,
            name,
            subtractBonus,
            collectionCeil,
            rolloverLimit,
            idManufacturerId,
            idFormulaId,
            idModelId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModelCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (subtractBonus != null ? "subtractBonus=" + subtractBonus + ", " : "") +
            (collectionCeil != null ? "collectionCeil=" + collectionCeil + ", " : "") +
            (rolloverLimit != null ? "rolloverLimit=" + rolloverLimit + ", " : "") +
            (idManufacturerId != null ? "idManufacturerId=" + idManufacturerId + ", " : "") +
            (idFormulaId != null ? "idFormulaId=" + idFormulaId + ", " : "") +
            (idModelId != null ? "idModelId=" + idModelId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
