package co.com.ies.smol.domain;

import co.com.ies.smol.domain.enumeration.EstablishmentType;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Establishment.
 */
@Entity
@Table(name = "establishment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Establishment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "liquidation_time")
    private ZonedDateTime liquidationTime;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EstablishmentType type;

    @Column(name = "municipality_code")
    private String municipalityCode;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "address")
    private String address;

    @Column(name = "coljuegos_code")
    private String coljuegosCode;

    @Column(name = "close_time")
    private ZonedDateTime closeTime;

    @Column(name = "start_time")
    private ZonedDateTime startTime;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "mercantile_registration")
    private String mercantileRegistration;

    @ManyToOne
    private Operator idOperator;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Establishment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getLiquidationTime() {
        return this.liquidationTime;
    }

    public Establishment liquidationTime(ZonedDateTime liquidationTime) {
        this.setLiquidationTime(liquidationTime);
        return this;
    }

    public void setLiquidationTime(ZonedDateTime liquidationTime) {
        this.liquidationTime = liquidationTime;
    }

    public String getName() {
        return this.name;
    }

    public Establishment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EstablishmentType getType() {
        return this.type;
    }

    public Establishment type(EstablishmentType type) {
        this.setType(type);
        return this;
    }

    public void setType(EstablishmentType type) {
        this.type = type;
    }

    public String getMunicipalityCode() {
        return this.municipalityCode;
    }

    public Establishment municipalityCode(String municipalityCode) {
        this.setMunicipalityCode(municipalityCode);
        return this;
    }

    public void setMunicipalityCode(String municipalityCode) {
        this.municipalityCode = municipalityCode;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public Establishment neighborhood(String neighborhood) {
        this.setNeighborhood(neighborhood);
        return this;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return this.address;
    }

    public Establishment address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColjuegosCode() {
        return this.coljuegosCode;
    }

    public Establishment coljuegosCode(String coljuegosCode) {
        this.setColjuegosCode(coljuegosCode);
        return this;
    }

    public void setColjuegosCode(String coljuegosCode) {
        this.coljuegosCode = coljuegosCode;
    }

    public ZonedDateTime getCloseTime() {
        return this.closeTime;
    }

    public Establishment closeTime(ZonedDateTime closeTime) {
        this.setCloseTime(closeTime);
        return this;
    }

    public void setCloseTime(ZonedDateTime closeTime) {
        this.closeTime = closeTime;
    }

    public ZonedDateTime getStartTime() {
        return this.startTime;
    }

    public Establishment startTime(ZonedDateTime startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public String getActivityType() {
        return this.activityType;
    }

    public Establishment activityType(String activityType) {
        this.setActivityType(activityType);
        return this;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Float getLongitude() {
        return this.longitude;
    }

    public Establishment longitude(Float longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return this.latitude;
    }

    public Establishment latitude(Float latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getMercantileRegistration() {
        return this.mercantileRegistration;
    }

    public Establishment mercantileRegistration(String mercantileRegistration) {
        this.setMercantileRegistration(mercantileRegistration);
        return this;
    }

    public void setMercantileRegistration(String mercantileRegistration) {
        this.mercantileRegistration = mercantileRegistration;
    }

    public Operator getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(Operator operator) {
        this.idOperator = operator;
    }

    public Establishment idOperator(Operator operator) {
        this.setIdOperator(operator);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Establishment)) {
            return false;
        }
        return id != null && id.equals(((Establishment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Establishment{" +
            "id=" + getId() +
            ", liquidationTime='" + getLiquidationTime() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", municipalityCode='" + getMunicipalityCode() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", address='" + getAddress() + "'" +
            ", coljuegosCode='" + getColjuegosCode() + "'" +
            ", closeTime='" + getCloseTime() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", activityType='" + getActivityType() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", mercantileRegistration='" + getMercantileRegistration() + "'" +
            "}";
    }
}
