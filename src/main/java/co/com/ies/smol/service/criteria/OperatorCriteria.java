package co.com.ies.smol.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.Operator} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.OperatorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /operators?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperatorCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter permitDescription;

    private ZonedDateTimeFilter endDate;

    private ZonedDateTimeFilter startDate;

    private BigDecimalFilter minAccumulatedPrize;

    private BigDecimalFilter minIndividualPrize;

    private BigDecimalFilter minTransactionAccumulated;

    private BigDecimalFilter minIndividualTransaction;

    private StringFilter nit;

    private StringFilter contractNumber;

    private IntegerFilter eventQuantity;

    private StringFilter companyName;

    private StringFilter municipalityCode;

    private StringFilter brand;

    private Boolean distinct;

    public OperatorCriteria() {}

    public OperatorCriteria(OperatorCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.permitDescription = other.permitDescription == null ? null : other.permitDescription.copy();
        this.endDate = other.endDate == null ? null : other.endDate.copy();
        this.startDate = other.startDate == null ? null : other.startDate.copy();
        this.minAccumulatedPrize = other.minAccumulatedPrize == null ? null : other.minAccumulatedPrize.copy();
        this.minIndividualPrize = other.minIndividualPrize == null ? null : other.minIndividualPrize.copy();
        this.minTransactionAccumulated = other.minTransactionAccumulated == null ? null : other.minTransactionAccumulated.copy();
        this.minIndividualTransaction = other.minIndividualTransaction == null ? null : other.minIndividualTransaction.copy();
        this.nit = other.nit == null ? null : other.nit.copy();
        this.contractNumber = other.contractNumber == null ? null : other.contractNumber.copy();
        this.eventQuantity = other.eventQuantity == null ? null : other.eventQuantity.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.municipalityCode = other.municipalityCode == null ? null : other.municipalityCode.copy();
        this.brand = other.brand == null ? null : other.brand.copy();
        this.distinct = other.distinct;
    }

    @Override
    public OperatorCriteria copy() {
        return new OperatorCriteria(this);
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

    public StringFilter getPermitDescription() {
        return permitDescription;
    }

    public StringFilter permitDescription() {
        if (permitDescription == null) {
            permitDescription = new StringFilter();
        }
        return permitDescription;
    }

    public void setPermitDescription(StringFilter permitDescription) {
        this.permitDescription = permitDescription;
    }

    public ZonedDateTimeFilter getEndDate() {
        return endDate;
    }

    public ZonedDateTimeFilter endDate() {
        if (endDate == null) {
            endDate = new ZonedDateTimeFilter();
        }
        return endDate;
    }

    public void setEndDate(ZonedDateTimeFilter endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTimeFilter getStartDate() {
        return startDate;
    }

    public ZonedDateTimeFilter startDate() {
        if (startDate == null) {
            startDate = new ZonedDateTimeFilter();
        }
        return startDate;
    }

    public void setStartDate(ZonedDateTimeFilter startDate) {
        this.startDate = startDate;
    }

    public BigDecimalFilter getMinAccumulatedPrize() {
        return minAccumulatedPrize;
    }

    public BigDecimalFilter minAccumulatedPrize() {
        if (minAccumulatedPrize == null) {
            minAccumulatedPrize = new BigDecimalFilter();
        }
        return minAccumulatedPrize;
    }

    public void setMinAccumulatedPrize(BigDecimalFilter minAccumulatedPrize) {
        this.minAccumulatedPrize = minAccumulatedPrize;
    }

    public BigDecimalFilter getMinIndividualPrize() {
        return minIndividualPrize;
    }

    public BigDecimalFilter minIndividualPrize() {
        if (minIndividualPrize == null) {
            minIndividualPrize = new BigDecimalFilter();
        }
        return minIndividualPrize;
    }

    public void setMinIndividualPrize(BigDecimalFilter minIndividualPrize) {
        this.minIndividualPrize = minIndividualPrize;
    }

    public BigDecimalFilter getMinTransactionAccumulated() {
        return minTransactionAccumulated;
    }

    public BigDecimalFilter minTransactionAccumulated() {
        if (minTransactionAccumulated == null) {
            minTransactionAccumulated = new BigDecimalFilter();
        }
        return minTransactionAccumulated;
    }

    public void setMinTransactionAccumulated(BigDecimalFilter minTransactionAccumulated) {
        this.minTransactionAccumulated = minTransactionAccumulated;
    }

    public BigDecimalFilter getMinIndividualTransaction() {
        return minIndividualTransaction;
    }

    public BigDecimalFilter minIndividualTransaction() {
        if (minIndividualTransaction == null) {
            minIndividualTransaction = new BigDecimalFilter();
        }
        return minIndividualTransaction;
    }

    public void setMinIndividualTransaction(BigDecimalFilter minIndividualTransaction) {
        this.minIndividualTransaction = minIndividualTransaction;
    }

    public StringFilter getNit() {
        return nit;
    }

    public StringFilter nit() {
        if (nit == null) {
            nit = new StringFilter();
        }
        return nit;
    }

    public void setNit(StringFilter nit) {
        this.nit = nit;
    }

    public StringFilter getContractNumber() {
        return contractNumber;
    }

    public StringFilter contractNumber() {
        if (contractNumber == null) {
            contractNumber = new StringFilter();
        }
        return contractNumber;
    }

    public void setContractNumber(StringFilter contractNumber) {
        this.contractNumber = contractNumber;
    }

    public IntegerFilter getEventQuantity() {
        return eventQuantity;
    }

    public IntegerFilter eventQuantity() {
        if (eventQuantity == null) {
            eventQuantity = new IntegerFilter();
        }
        return eventQuantity;
    }

    public void setEventQuantity(IntegerFilter eventQuantity) {
        this.eventQuantity = eventQuantity;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public StringFilter companyName() {
        if (companyName == null) {
            companyName = new StringFilter();
        }
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
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

    public StringFilter getBrand() {
        return brand;
    }

    public StringFilter brand() {
        if (brand == null) {
            brand = new StringFilter();
        }
        return brand;
    }

    public void setBrand(StringFilter brand) {
        this.brand = brand;
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
        final OperatorCriteria that = (OperatorCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(permitDescription, that.permitDescription) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(minAccumulatedPrize, that.minAccumulatedPrize) &&
            Objects.equals(minIndividualPrize, that.minIndividualPrize) &&
            Objects.equals(minTransactionAccumulated, that.minTransactionAccumulated) &&
            Objects.equals(minIndividualTransaction, that.minIndividualTransaction) &&
            Objects.equals(nit, that.nit) &&
            Objects.equals(contractNumber, that.contractNumber) &&
            Objects.equals(eventQuantity, that.eventQuantity) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(municipalityCode, that.municipalityCode) &&
            Objects.equals(brand, that.brand) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            permitDescription,
            endDate,
            startDate,
            minAccumulatedPrize,
            minIndividualPrize,
            minTransactionAccumulated,
            minIndividualTransaction,
            nit,
            contractNumber,
            eventQuantity,
            companyName,
            municipalityCode,
            brand,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (permitDescription != null ? "permitDescription=" + permitDescription + ", " : "") +
            (endDate != null ? "endDate=" + endDate + ", " : "") +
            (startDate != null ? "startDate=" + startDate + ", " : "") +
            (minAccumulatedPrize != null ? "minAccumulatedPrize=" + minAccumulatedPrize + ", " : "") +
            (minIndividualPrize != null ? "minIndividualPrize=" + minIndividualPrize + ", " : "") +
            (minTransactionAccumulated != null ? "minTransactionAccumulated=" + minTransactionAccumulated + ", " : "") +
            (minIndividualTransaction != null ? "minIndividualTransaction=" + minIndividualTransaction + ", " : "") +
            (nit != null ? "nit=" + nit + ", " : "") +
            (contractNumber != null ? "contractNumber=" + contractNumber + ", " : "") +
            (eventQuantity != null ? "eventQuantity=" + eventQuantity + ", " : "") +
            (companyName != null ? "companyName=" + companyName + ", " : "") +
            (municipalityCode != null ? "municipalityCode=" + municipalityCode + ", " : "") +
            (brand != null ? "brand=" + brand + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
