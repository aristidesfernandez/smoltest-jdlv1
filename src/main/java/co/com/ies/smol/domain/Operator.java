package co.com.ies.smol.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Operator.
 */
@Entity
@Table(name = "operator")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "permit_description")
    private String permitDescription;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "min_accumulated_prize", precision = 21, scale = 2)
    private BigDecimal minAccumulatedPrize;

    @Column(name = "min_individual_prize", precision = 21, scale = 2)
    private BigDecimal minIndividualPrize;

    @Column(name = "min_transaction_accumulated", precision = 21, scale = 2)
    private BigDecimal minTransactionAccumulated;

    @Column(name = "min_individual_transaction", precision = 21, scale = 2)
    private BigDecimal minIndividualTransaction;

    @Column(name = "nit")
    private String nit;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "event_quantity")
    private Integer eventQuantity;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "municipality_code")
    private String municipalityCode;

    @Column(name = "brand")
    private String brand;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Operator id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermitDescription() {
        return this.permitDescription;
    }

    public Operator permitDescription(String permitDescription) {
        this.setPermitDescription(permitDescription);
        return this;
    }

    public void setPermitDescription(String permitDescription) {
        this.permitDescription = permitDescription;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public Operator endDate(ZonedDateTime endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public Operator startDate(ZonedDateTime startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getMinAccumulatedPrize() {
        return this.minAccumulatedPrize;
    }

    public Operator minAccumulatedPrize(BigDecimal minAccumulatedPrize) {
        this.setMinAccumulatedPrize(minAccumulatedPrize);
        return this;
    }

    public void setMinAccumulatedPrize(BigDecimal minAccumulatedPrize) {
        this.minAccumulatedPrize = minAccumulatedPrize;
    }

    public BigDecimal getMinIndividualPrize() {
        return this.minIndividualPrize;
    }

    public Operator minIndividualPrize(BigDecimal minIndividualPrize) {
        this.setMinIndividualPrize(minIndividualPrize);
        return this;
    }

    public void setMinIndividualPrize(BigDecimal minIndividualPrize) {
        this.minIndividualPrize = minIndividualPrize;
    }

    public BigDecimal getMinTransactionAccumulated() {
        return this.minTransactionAccumulated;
    }

    public Operator minTransactionAccumulated(BigDecimal minTransactionAccumulated) {
        this.setMinTransactionAccumulated(minTransactionAccumulated);
        return this;
    }

    public void setMinTransactionAccumulated(BigDecimal minTransactionAccumulated) {
        this.minTransactionAccumulated = minTransactionAccumulated;
    }

    public BigDecimal getMinIndividualTransaction() {
        return this.minIndividualTransaction;
    }

    public Operator minIndividualTransaction(BigDecimal minIndividualTransaction) {
        this.setMinIndividualTransaction(minIndividualTransaction);
        return this;
    }

    public void setMinIndividualTransaction(BigDecimal minIndividualTransaction) {
        this.minIndividualTransaction = minIndividualTransaction;
    }

    public String getNit() {
        return this.nit;
    }

    public Operator nit(String nit) {
        this.setNit(nit);
        return this;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public Operator contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Integer getEventQuantity() {
        return this.eventQuantity;
    }

    public Operator eventQuantity(Integer eventQuantity) {
        this.setEventQuantity(eventQuantity);
        return this;
    }

    public void setEventQuantity(Integer eventQuantity) {
        this.eventQuantity = eventQuantity;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public Operator companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMunicipalityCode() {
        return this.municipalityCode;
    }

    public Operator municipalityCode(String municipalityCode) {
        this.setMunicipalityCode(municipalityCode);
        return this;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getBrand() {
        return this.brand;
    }

    public Operator brand(String brand) {
        this.setBrand(brand);
        return this;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operator)) {
            return false;
        }
        return id != null && id.equals(((Operator) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Operator{" +
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
