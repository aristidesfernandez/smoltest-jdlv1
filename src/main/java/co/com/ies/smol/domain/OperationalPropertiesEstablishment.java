package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OperationalPropertiesEstablishment.
 */
@Entity
@Table(name = "operational_properties_establishment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationalPropertiesEstablishment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "key")
    private Integer key;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idOperator" }, allowSetters = true)
    private Establishment idEstablishment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OperationalPropertiesEstablishment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public OperationalPropertiesEstablishment value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getKey() {
        return this.key;
    }

    public OperationalPropertiesEstablishment key(Integer key) {
        this.setKey(key);
        return this;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Establishment getIdEstablishment() {
        return this.idEstablishment;
    }

    public void setIdEstablishment(Establishment establishment) {
        this.idEstablishment = establishment;
    }

    public OperationalPropertiesEstablishment idEstablishment(Establishment establishment) {
        this.setIdEstablishment(establishment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationalPropertiesEstablishment)) {
            return false;
        }
        return id != null && id.equals(((OperationalPropertiesEstablishment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationalPropertiesEstablishment{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", key=" + getKey() +
            "}";
    }
}
