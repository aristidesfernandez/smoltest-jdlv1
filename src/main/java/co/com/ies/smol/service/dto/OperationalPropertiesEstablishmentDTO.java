package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.com.ies.smol.domain.OperationalPropertiesEstablishment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OperationalPropertiesEstablishmentDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String value;

    private Integer key;

    private EstablishmentDTO establishment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public EstablishmentDTO getEstablishment() {
        return establishment;
    }

    public void setEstablishment(EstablishmentDTO establishment) {
        this.establishment = establishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OperationalPropertiesEstablishmentDTO)) {
            return false;
        }

        OperationalPropertiesEstablishmentDTO operationalPropertiesEstablishmentDTO = (OperationalPropertiesEstablishmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, operationalPropertiesEstablishmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OperationalPropertiesEstablishmentDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", key=" + getKey() +
            ", establishment=" + getEstablishment() +
            "}";
    }
}
