package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DeviceEstablishment.
 */
@Entity
@Table(name = "device_establishment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeviceEstablishment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "registration_at", nullable = false)
    private ZonedDateTime registrationAt;

    @NotNull
    @Size(max = 50)
    @Column(name = "serial", length = 50, nullable = false)
    private String serial;

    @Column(name = "departure_at")
    private ZonedDateTime departureAt;

    @Column(name = "device_number")
    private Integer deviceNumber;

    @Column(name = "consecutive_device")
    private Integer consecutiveDevice;

    @Column(name = "negative_award")
    private Float negativeAward;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "operator" }, allowSetters = true)
    private Establishment establishment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public DeviceEstablishment id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getRegistrationAt() {
        return this.registrationAt;
    }

    public DeviceEstablishment registrationAt(ZonedDateTime registrationAt) {
        this.setRegistrationAt(registrationAt);
        return this;
    }

    public void setRegistrationAt(ZonedDateTime registrationAt) {
        this.registrationAt = registrationAt;
    }

    public String getSerial() {
        return this.serial;
    }

    public DeviceEstablishment serial(String serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public ZonedDateTime getDepartureAt() {
        return this.departureAt;
    }

    public DeviceEstablishment departureAt(ZonedDateTime departureAt) {
        this.setDepartureAt(departureAt);
        return this;
    }

    public void setDepartureAt(ZonedDateTime departureAt) {
        this.departureAt = departureAt;
    }

    public Integer getDeviceNumber() {
        return this.deviceNumber;
    }

    public DeviceEstablishment deviceNumber(Integer deviceNumber) {
        this.setDeviceNumber(deviceNumber);
        return this;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Integer getConsecutiveDevice() {
        return this.consecutiveDevice;
    }

    public DeviceEstablishment consecutiveDevice(Integer consecutiveDevice) {
        this.setConsecutiveDevice(consecutiveDevice);
        return this;
    }

    public void setConsecutiveDevice(Integer consecutiveDevice) {
        this.consecutiveDevice = consecutiveDevice;
    }

    public Float getNegativeAward() {
        return this.negativeAward;
    }

    public DeviceEstablishment negativeAward(Float negativeAward) {
        this.setNegativeAward(negativeAward);
        return this;
    }

    public void setNegativeAward(Float negativeAward) {
        this.negativeAward = negativeAward;
    }

    public Establishment getEstablishment() {
        return this.establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    public DeviceEstablishment establishment(Establishment establishment) {
        this.setEstablishment(establishment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceEstablishment)) {
            return false;
        }
        return id != null && id.equals(((DeviceEstablishment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceEstablishment{" +
            "id=" + getId() +
            ", registrationAt='" + getRegistrationAt() + "'" +
            ", serial='" + getSerial() + "'" +
            ", departureAt='" + getDepartureAt() + "'" +
            ", deviceNumber=" + getDeviceNumber() +
            ", consecutiveDevice=" + getConsecutiveDevice() +
            ", negativeAward=" + getNegativeAward() +
            "}";
    }
}
