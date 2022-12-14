package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EventDevice.
 */
@Entity
@Table(name = "event_device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EventDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "theoretical_percentage")
    private Boolean theoreticalPercentage;

    @Column(name = "money_denomination")
    private Double moneyDenomination;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idOperator" }, allowSetters = true)
    private Establishment idEstablishment;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idEventTypes" }, allowSetters = true)
    private EventType idEventType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EventDevice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public EventDevice createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getTheoreticalPercentage() {
        return this.theoreticalPercentage;
    }

    public EventDevice theoreticalPercentage(Boolean theoreticalPercentage) {
        this.setTheoreticalPercentage(theoreticalPercentage);
        return this;
    }

    public void setTheoreticalPercentage(Boolean theoreticalPercentage) {
        this.theoreticalPercentage = theoreticalPercentage;
    }

    public Double getMoneyDenomination() {
        return this.moneyDenomination;
    }

    public EventDevice moneyDenomination(Double moneyDenomination) {
        this.setMoneyDenomination(moneyDenomination);
        return this;
    }

    public void setMoneyDenomination(Double moneyDenomination) {
        this.moneyDenomination = moneyDenomination;
    }

    public Establishment getIdEstablishment() {
        return this.idEstablishment;
    }

    public void setIdEstablishment(Establishment establishment) {
        this.idEstablishment = establishment;
    }

    public EventDevice idEstablishment(Establishment establishment) {
        this.setIdEstablishment(establishment);
        return this;
    }

    public EventType getIdEventType() {
        return this.idEventType;
    }

    public void setIdEventType(EventType eventType) {
        this.idEventType = eventType;
    }

    public EventDevice idEventType(EventType eventType) {
        this.setIdEventType(eventType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventDevice)) {
            return false;
        }
        return id != null && id.equals(((EventDevice) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventDevice{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", theoreticalPercentage='" + getTheoreticalPercentage() + "'" +
            ", moneyDenomination=" + getMoneyDenomination() +
            "}";
    }
}
