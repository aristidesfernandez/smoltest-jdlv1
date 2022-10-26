package co.com.ies.smol.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 25)
    @Column(name = "code", length = 25)
    private String code;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 25)
    @Column(name = "dane_code", length = 25)
    private String daneCode;

    @Size(max = 15)
    @Column(name = "phone_id", length = 15)
    private String phoneId;

    @ManyToOne
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Department id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Department code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Department name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDaneCode() {
        return this.daneCode;
    }

    public Department daneCode(String daneCode) {
        this.setDaneCode(daneCode);
        return this;
    }

    public void setDaneCode(String daneCode) {
        this.daneCode = daneCode;
    }

    public String getPhoneId() {
        return this.phoneId;
    }

    public Department phoneId(String phoneId) {
        this.setPhoneId(phoneId);
        return this;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Department country(Country country) {
        this.setCountry(country);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", daneCode='" + getDaneCode() + "'" +
            ", phoneId='" + getPhoneId() + "'" +
            "}";
    }
}
