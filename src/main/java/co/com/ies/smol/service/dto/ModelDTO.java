package co.com.ies.smol.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.com.ies.smol.domain.Model} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ModelDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    private String name;

    private Boolean subtractBonus;

    private BigDecimal collectionCeil;

    private BigDecimal rolloverLimit;

    private ManufacturerDTO idManufacturer;

    private FormulaDTO idFormula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSubtractBonus() {
        return subtractBonus;
    }

    public void setSubtractBonus(Boolean subtractBonus) {
        this.subtractBonus = subtractBonus;
    }

    public BigDecimal getCollectionCeil() {
        return collectionCeil;
    }

    public void setCollectionCeil(BigDecimal collectionCeil) {
        this.collectionCeil = collectionCeil;
    }

    public BigDecimal getRolloverLimit() {
        return rolloverLimit;
    }

    public void setRolloverLimit(BigDecimal rolloverLimit) {
        this.rolloverLimit = rolloverLimit;
    }

    public ManufacturerDTO getIdManufacturer() {
        return idManufacturer;
    }

    public void setIdManufacturer(ManufacturerDTO idManufacturer) {
        this.idManufacturer = idManufacturer;
    }

    public FormulaDTO getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(FormulaDTO idFormula) {
        this.idFormula = idFormula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModelDTO)) {
            return false;
        }

        ModelDTO modelDTO = (ModelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, modelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ModelDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", subtractBonus='" + getSubtractBonus() + "'" +
            ", collectionCeil=" + getCollectionCeil() +
            ", rolloverLimit=" + getRolloverLimit() +
            ", idManufacturer=" + getIdManufacturer() +
            ", idFormula=" + getIdFormula() +
            "}";
    }
}
