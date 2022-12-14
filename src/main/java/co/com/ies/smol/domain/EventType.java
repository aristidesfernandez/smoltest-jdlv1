package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EventType.
 */
@Entity
@Table(name = "event_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EventType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "event_code", nullable = false)
    private String eventCode;

    @Column(name = "sas_code")
    private String sasCode;

    @Column(name = "description")
    private String description;

    @Column(name = "is_storable")
    private Boolean isStorable;

    @Column(name = "is_priority")
    private Boolean isPriority;

    @Column(name = "procesador")
    private String procesador;

    @Column(name = "is_alarm")
    private Boolean isAlarm;

    @ManyToMany
    @JoinTable(
        name = "rel_event_type__id_event_type",
        joinColumns = @JoinColumn(name = "event_type_id"),
        inverseJoinColumns = @JoinColumn(name = "id_event_type_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "idManufacturer", "idFormula", "idModels" }, allowSetters = true)
    private Set<Model> idEventTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EventType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventCode() {
        return this.eventCode;
    }

    public EventType eventCode(String eventCode) {
        this.setEventCode(eventCode);
        return this;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getSasCode() {
        return this.sasCode;
    }

    public EventType sasCode(String sasCode) {
        this.setSasCode(sasCode);
        return this;
    }

    public void setSasCode(String sasCode) {
        this.sasCode = sasCode;
    }

    public String getDescription() {
        return this.description;
    }

    public EventType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsStorable() {
        return this.isStorable;
    }

    public EventType isStorable(Boolean isStorable) {
        this.setIsStorable(isStorable);
        return this;
    }

    public void setIsStorable(Boolean isStorable) {
        this.isStorable = isStorable;
    }

    public Boolean getIsPriority() {
        return this.isPriority;
    }

    public EventType isPriority(Boolean isPriority) {
        this.setIsPriority(isPriority);
        return this;
    }

    public void setIsPriority(Boolean isPriority) {
        this.isPriority = isPriority;
    }

    public String getProcesador() {
        return this.procesador;
    }

    public EventType procesador(String procesador) {
        this.setProcesador(procesador);
        return this;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Boolean getIsAlarm() {
        return this.isAlarm;
    }

    public EventType isAlarm(Boolean isAlarm) {
        this.setIsAlarm(isAlarm);
        return this;
    }

    public void setIsAlarm(Boolean isAlarm) {
        this.isAlarm = isAlarm;
    }

    public Set<Model> getIdEventTypes() {
        return this.idEventTypes;
    }

    public void setIdEventTypes(Set<Model> models) {
        this.idEventTypes = models;
    }

    public EventType idEventTypes(Set<Model> models) {
        this.setIdEventTypes(models);
        return this;
    }

    public EventType addIdEventType(Model model) {
        this.idEventTypes.add(model);
        model.getIdModels().add(this);
        return this;
    }

    public EventType removeIdEventType(Model model) {
        this.idEventTypes.remove(model);
        model.getIdModels().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventType)) {
            return false;
        }
        return id != null && id.equals(((EventType) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EventType{" +
            "id=" + getId() +
            ", eventCode='" + getEventCode() + "'" +
            ", sasCode='" + getSasCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", isStorable='" + getIsStorable() + "'" +
            ", isPriority='" + getIsPriority() + "'" +
            ", procesador='" + getProcesador() + "'" +
            ", isAlarm='" + getIsAlarm() + "'" +
            "}";
    }
}
