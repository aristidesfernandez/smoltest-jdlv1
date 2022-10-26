package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Model.
 */
@Entity
@Table(name = "model")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "subtract_bonus")
    private Boolean subtractBonus;

    @Column(name = "collection_ceil", precision = 21, scale = 2)
    private BigDecimal collectionCeil;

    @Column(name = "rollover_limit", precision = 21, scale = 2)
    private BigDecimal rolloverLimit;

    @ManyToOne(optional = false)
    @NotNull
    private Manufacturer idManufacturer;

    @ManyToOne(optional = false)
    @NotNull
    private Formula idFormula;

    @ManyToMany(mappedBy = "idEventTypes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idEventTypes" }, allowSetters = true)
    private Set<EventType> idModels = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Model id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Model code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Model name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubtractBonus() {
        return this.subtractBonus;
    }

    public Model subtractBonus(Boolean subtractBonus) {
        this.setSubtractBonus(subtractBonus);
        return this;
    }

    public void setSubtractBonus(Boolean subtractBonus) {
        this.subtractBonus = subtractBonus;
    }

    public BigDecimal getCollectionCeil() {
        return this.collectionCeil;
    }

    public Model collectionCeil(BigDecimal collectionCeil) {
        this.setCollectionCeil(collectionCeil);
        return this;
    }

    public void setCollectionCeil(BigDecimal collectionCeil) {
        this.collectionCeil = collectionCeil;
    }

    public BigDecimal getRolloverLimit() {
        return this.rolloverLimit;
    }

    public Model rolloverLimit(BigDecimal rolloverLimit) {
        this.setRolloverLimit(rolloverLimit);
        return this;
    }

    public void setRolloverLimit(BigDecimal rolloverLimit) {
        this.rolloverLimit = rolloverLimit;
    }

    public Manufacturer getIdManufacturer() {
        return this.idManufacturer;
    }

    public void setIdManufacturer(Manufacturer manufacturer) {
        this.idManufacturer = manufacturer;
    }

    public Model idManufacturer(Manufacturer manufacturer) {
        this.setIdManufacturer(manufacturer);
        return this;
    }

    public Formula getIdFormula() {
        return this.idFormula;
    }

    public void setIdFormula(Formula formula) {
        this.idFormula = formula;
    }

    public Model idFormula(Formula formula) {
        this.setIdFormula(formula);
        return this;
    }

    public Set<EventType> getIdModels() {
        return this.idModels;
    }

    public void setIdModels(Set<EventType> eventTypes) {
        if (this.idModels != null) {
            this.idModels.forEach(i -> i.removeIdEventType(this));
        }
        if (eventTypes != null) {
            eventTypes.forEach(i -> i.addIdEventType(this));
        }
        this.idModels = eventTypes;
    }

    public Model idModels(Set<EventType> eventTypes) {
        this.setIdModels(eventTypes);
        return this;
    }

    public Model addIdModel(EventType eventType) {
        this.idModels.add(eventType);
        eventType.getIdEventTypes().add(this);
        return this;
    }

    public Model removeIdModel(EventType eventType) {
        this.idModels.remove(eventType);
        eventType.getIdEventTypes().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }
        return id != null && id.equals(((Model) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Model{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", subtractBonus='" + getSubtractBonus() + "'" +
            ", collectionCeil=" + getCollectionCeil() +
            ", rolloverLimit=" + getRolloverLimit() +
            "}";
    }
}
