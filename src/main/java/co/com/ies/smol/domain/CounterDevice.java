package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CounterDevice.
 */
@Entity
@Table(name = "counter_device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CounterDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value", precision = 21, scale = 2)
    private BigDecimal value;

    @Column(name = "rollover_value", precision = 21, scale = 2)
    private BigDecimal rolloverValue;

    @Column(name = "credit_sale", precision = 21, scale = 2)
    private BigDecimal creditSale;

    @Column(name = "manual_counter")
    private Boolean manualCounter;

    @Column(name = "manual_multiplier", precision = 21, scale = 2)
    private BigDecimal manualMultiplier;

    @Column(name = "decimals_manual_counter")
    private Boolean decimalsManualCounter;

    @ManyToOne(optional = false)
    @NotNull
    private CounterType counterCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(
        value = {
            "idInterfacing",
            "idModel",
            "idDeviceCategory",
            "idDeviceType",
            "idFormulaHandpay",
            "idFormulaJackpot",
            "idIsland",
            "idCurrencyType",
        },
        allowSetters = true
    )
    private Device idDevice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CounterDevice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public CounterDevice value(BigDecimal value) {
        this.setValue(value);
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getRolloverValue() {
        return this.rolloverValue;
    }

    public CounterDevice rolloverValue(BigDecimal rolloverValue) {
        this.setRolloverValue(rolloverValue);
        return this;
    }

    public void setRolloverValue(BigDecimal rolloverValue) {
        this.rolloverValue = rolloverValue;
    }

    public BigDecimal getCreditSale() {
        return this.creditSale;
    }

    public CounterDevice creditSale(BigDecimal creditSale) {
        this.setCreditSale(creditSale);
        return this;
    }

    public void setCreditSale(BigDecimal creditSale) {
        this.creditSale = creditSale;
    }

    public Boolean getManualCounter() {
        return this.manualCounter;
    }

    public CounterDevice manualCounter(Boolean manualCounter) {
        this.setManualCounter(manualCounter);
        return this;
    }

    public void setManualCounter(Boolean manualCounter) {
        this.manualCounter = manualCounter;
    }

    public BigDecimal getManualMultiplier() {
        return this.manualMultiplier;
    }

    public CounterDevice manualMultiplier(BigDecimal manualMultiplier) {
        this.setManualMultiplier(manualMultiplier);
        return this;
    }

    public void setManualMultiplier(BigDecimal manualMultiplier) {
        this.manualMultiplier = manualMultiplier;
    }

    public Boolean getDecimalsManualCounter() {
        return this.decimalsManualCounter;
    }

    public CounterDevice decimalsManualCounter(Boolean decimalsManualCounter) {
        this.setDecimalsManualCounter(decimalsManualCounter);
        return this;
    }

    public void setDecimalsManualCounter(Boolean decimalsManualCounter) {
        this.decimalsManualCounter = decimalsManualCounter;
    }

    public CounterType getCounterCode() {
        return this.counterCode;
    }

    public void setCounterCode(CounterType counterType) {
        this.counterCode = counterType;
    }

    public CounterDevice counterCode(CounterType counterType) {
        this.setCounterCode(counterType);
        return this;
    }

    public Device getIdDevice() {
        return this.idDevice;
    }

    public void setIdDevice(Device device) {
        this.idDevice = device;
    }

    public CounterDevice idDevice(Device device) {
        this.setIdDevice(device);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CounterDevice)) {
            return false;
        }
        return id != null && id.equals(((CounterDevice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CounterDevice{" +
            "id=" + getId() +
            ", value=" + getValue() +
            ", rolloverValue=" + getRolloverValue() +
            ", creditSale=" + getCreditSale() +
            ", manualCounter='" + getManualCounter() + "'" +
            ", manualMultiplier=" + getManualMultiplier() +
            ", decimalsManualCounter='" + getDecimalsManualCounter() + "'" +
            "}";
    }
}
