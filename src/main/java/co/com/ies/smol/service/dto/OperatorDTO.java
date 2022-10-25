package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link co.com.ies.smol.domain.Operator} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperatorDTO implements Serializable {

    private Long id;

    private String permitDescription;

    private ZonedDateTime endDate;

    private ZonedDateTime startDate;

    private BigDecimal minAccumulatedPrize;

    private BigDecimal minIndividualPrize;

    private BigDecimal minTransactionAccumulated;

    private BigDecimal minIndividualTransaction;

    private String nit;

    private String contractNumber;

    private Integer eventQuantity;

    private String companyName;

    private String municipalityCode;

    private String brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermitDescription() {
        return permitDescription;
    }

    public void setPermitDescription(String permitDescription) {
        this.permitDescription = permitDescription;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getMinAccumulatedPrize() {
        return minAccumulatedPrize;
    }

    public void setMinAccumulatedPrize(BigDecimal minAccumulatedPrize) {
        this.minAccumulatedPrize = minAccumulatedPrize;
    }

    public BigDecimal getMinIndividualPrize() {
        return minIndividualPrize;
    }

    public void setMinIndividualPrize(BigDecimal minIndividualPrize) {
        this.minIndividualPrize = minIndividualPrize;
    }

    public BigDecimal getMinTransactionAccumulated() {
        return minTransactionAccumulated;
    }

    public void setMinTransactionAccumulated(BigDecimal minTransactionAccumulated) {
        this.minTransactionAccumulated = minTransactionAccumulated;
    }

    public BigDecimal getMinIndividualTransaction() {
        return minIndividualTransaction;
    }

    public void setMinIndividualTransaction(BigDecimal minIndividualTransaction) {
        this.minIndividualTransaction = minIndividualTransaction;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getEventQuantity() {
        return eventQuantity;
    }

    public void setEventQuantity(Integer eventQuantity) {
        this.eventQuantity = eventQuantity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperatorDTO)) {
            return false;
        }

        OperatorDTO operatorDTO = (OperatorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, operatorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperatorDTO{" +
            "id=" + getId() +
            ", permitDescription='" + getPermitDescription() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", minAccumulatedPrize=" + getMinAccumulatedPrize() +
            ", minIndividualPrize=" + getMinIndividualPrize() +
            ", minTransactionAccumulated=" + getMinTransactionAccumulated() +
            ", minIndividualTransaction=" + getMinIndividualTransaction() +
            ", nit='" + getNit() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", eventQuantity=" + getEventQuantity() +
            ", companyName='" + getCompanyName() + "'" +
            ", municipalityCode='" + getMunicipalityCode() + "'" +
            ", brand='" + getBrand() + "'" +
            "}";
    }
}
