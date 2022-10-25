package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Interfacing.
 */
@Entity
@Table(name = "interfacing")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Interfacing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "hash")
    private String hash;

    @Column(name = "serial")
    private String serial;

    @Column(name = "version")
    private String version;

    @Column(name = "port")
    private String port;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idOperator" }, allowSetters = true)
    private Establishment idEstablishment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Interfacing id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAssigned() {
        return this.isAssigned;
    }

    public Interfacing isAssigned(Boolean isAssigned) {
        this.setIsAssigned(isAssigned);
        return this;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Interfacing ipAddress(String ipAddress) {
        this.setIpAddress(ipAddress);
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHash() {
        return this.hash;
    }

    public Interfacing hash(String hash) {
        this.setHash(hash);
        return this;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSerial() {
        return this.serial;
    }

    public Interfacing serial(String serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getVersion() {
        return this.version;
    }

    public Interfacing version(String version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPort() {
        return this.port;
    }

    public Interfacing port(String port) {
        this.setPort(port);
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Establishment getIdEstablishment() {
        return this.idEstablishment;
    }

    public void setIdEstablishment(Establishment establishment) {
        this.idEstablishment = establishment;
    }

    public Interfacing idEstablishment(Establishment establishment) {
        this.setIdEstablishment(establishment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Interfacing)) {
            return false;
        }
        return id != null && id.equals(((Interfacing) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Interfacing{" +
            "id=" + getId() +
            ", isAssigned='" + getIsAssigned() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", hash='" + getHash() + "'" +
            ", serial='" + getSerial() + "'" +
            ", version='" + getVersion() + "'" +
            ", port='" + getPort() + "'" +
            "}";
    }
}
