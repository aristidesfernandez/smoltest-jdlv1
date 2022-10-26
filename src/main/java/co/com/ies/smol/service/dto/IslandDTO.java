package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link co.com.ies.smol.domain.Island} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IslandDTO implements Serializable {

    private Long id;

    private String description;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IslandDTO)) {
            return false;
        }

        IslandDTO islandDTO = (IslandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, islandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IslandDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
