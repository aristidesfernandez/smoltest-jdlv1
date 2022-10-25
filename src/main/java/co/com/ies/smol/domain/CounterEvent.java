package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CounterEvent.
 */
@Entity
@Table(name = "counter_event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CounterEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value_counter")
    private Long valueCounter;

    @Column(name = "denomination_sale")
    private Float denominationSale;

    @ManyToOne(optional = false)
    @NotNull
    private CounterType counterCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idEstablishment", "idEventType" }, allowSetters = true)
    private EventDevice idEventDevice;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CounterEvent id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValueCounter() {
        return this.valueCounter;
    }

    public CounterEvent valueCounter(Long valueCounter) {
        this.setValueCounter(valueCounter);
        return this;
    }

    public void setValueCounter(Long valueCounter) {
        this.valueCounter = valueCounter;
    }

    public Float getDenominationSale() {
        return this.denominationSale;
    }

    public CounterEvent denominationSale(Float denominationSale) {
        this.setDenominationSale(denominationSale);
        return this;
    }

    public void setDenominationSale(Float denominationSale) {
        this.denominationSale = denominationSale;
    }

    public CounterType getCounterCode() {
        return this.counterCode;
    }

    public void setCounterCode(CounterType counterType) {
        this.counterCode = counterType;
    }

    public CounterEvent counterCode(CounterType counterType) {
        this.setCounterCode(counterType);
        return this;
    }

    public EventDevice getIdEventDevice() {
        return this.idEventDevice;
    }

    public void setIdEventDevice(EventDevice eventDevice) {
        this.idEventDevice = eventDevice;
    }

    public CounterEvent idEventDevice(EventDevice eventDevice) {
        this.setIdEventDevice(eventDevice);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CounterEvent)) {
            return false;
        }
        return id != null && id.equals(((CounterEvent) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CounterEvent{" +
            "id=" + getId() +
            ", valueCounter=" + getValueCounter() +
            ", denominationSale=" + getDenominationSale() +
            "}";
    }
}
