package co.com.ies.smol.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CounterType.
 */
@Entity
@Table(name = "counter_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CounterType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "counter_code", nullable = false)
    private String counterCode;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "included_in_formula")
    private Boolean includedInFormula;

    @Column(name = "prize")
    private Boolean prize;

    @Column(name = "category")
    private String category;

    @Column(name = "udte_wait_time")
    private Integer udteWaitTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CounterType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCounterCode() {
        return this.counterCode;
    }

    public CounterType counterCode(String counterCode) {
        this.setCounterCode(counterCode);
        return this;
    }

    public void setCounterCode(String counterCode) {
        this.counterCode = counterCode;
    }

    public String getName() {
        return this.name;
    }

    public CounterType name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public CounterType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIncludedInFormula() {
        return this.includedInFormula;
    }

    public CounterType includedInFormula(Boolean includedInFormula) {
        this.setIncludedInFormula(includedInFormula);
        return this;
    }

    public void setIncludedInFormula(Boolean includedInFormula) {
        this.includedInFormula = includedInFormula;
    }

    public Boolean getPrize() {
        return this.prize;
    }

    public CounterType prize(Boolean prize) {
        this.setPrize(prize);
        return this;
    }

    public void setPrize(Boolean prize) {
        this.prize = prize;
    }

    public String getCategory() {
        return this.category;
    }

    public CounterType category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUdteWaitTime() {
        return this.udteWaitTime;
    }

    public CounterType udteWaitTime(Integer udteWaitTime) {
        this.setUdteWaitTime(udteWaitTime);
        return this;
    }

    public void setUdteWaitTime(Integer udteWaitTime) {
        this.udteWaitTime = udteWaitTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CounterType)) {
            return false;
        }
        return id != null && id.equals(((CounterType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CounterType{" +
            "id=" + getId() +
            ", counterCode='" + getCounterCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", includedInFormula='" + getIncludedInFormula() + "'" +
            ", prize='" + getPrize() + "'" +
            ", category='" + getCategory() + "'" +
            ", udteWaitTime=" + getUdteWaitTime() +
            "}";
    }
}
