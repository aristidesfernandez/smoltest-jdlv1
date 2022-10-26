package co.com.ies.smol.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Device.
 */
@Entity
@Table(name = "device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "is_protocol_esdcs")
    private Boolean isProtocolEsdcs;

    @Column(name = "number_played_report")
    private Integer numberPlayedReport;

    @Column(name = "sas_denomination", precision = 21, scale = 2)
    private BigDecimal sasDenomination;

    @Column(name = "is_multigame")
    private Boolean isMultigame;

    @Column(name = "is_multi_denomination")
    private Boolean isMultiDenomination;

    @Column(name = "is_retanqueo")
    private Boolean isRetanqueo;

    @Column(name = "state")
    private String state;

    @Column(name = "theoretical_hold", precision = 21, scale = 2)
    private BigDecimal theoreticalHold;

    @Column(name = "sas_identifier")
    private Integer sasIdentifier;

    @Column(name = "credit_limit")
    private Long creditLimit;

    @Column(name = "has_hooper")
    private Boolean hasHooper;

    @Column(name = "coljuegos_code")
    private String coljuegosCode;

    @Column(name = "fabrication_date")
    private LocalDate fabricationDate;

    @Column(name = "current_token", precision = 21, scale = 2)
    private BigDecimal currentToken;

    @Column(name = "denomination_tito", precision = 21, scale = 2)
    private BigDecimal denominationTito;

    @Column(name = "end_lost_communication")
    private ZonedDateTime endLostCommunication;

    @Column(name = "start_lost_communication")
    private ZonedDateTime startLostCommunication;

    @Column(name = "report_multiplier", precision = 21, scale = 2)
    private BigDecimal reportMultiplier;

    @Column(name = "nuid")
    private String nuid;

    @Column(name = "pay_manual_prize")
    private Boolean payManualPrize;

    @Column(name = "manual_handpay")
    private Boolean manualHandpay;

    @Column(name = "manual_jackpot")
    private Boolean manualJackpot;

    @Column(name = "manual_game_event")
    private Boolean manualGameEvent;

    @Column(name = "bet_code")
    private String betCode;

    @Column(name = "homologation_indicator")
    private Boolean homologationIndicator;

    @Column(name = "coljuegos_model")
    private String coljuegosModel;

    @Column(name = "reportable")
    private Boolean reportable;

    @Column(name = "aft_denomination", precision = 21, scale = 2)
    private BigDecimal aftDenomination;

    @Column(name = "last_update_date")
    private ZonedDateTime lastUpdateDate;

    @Column(name = "enable_rollover")
    private Boolean enableRollover;

    @Column(name = "last_corruption_date")
    private ZonedDateTime lastCorruptionDate;

    @Column(name = "daft_denomination", precision = 21, scale = 2)
    private BigDecimal daftDenomination;

    @Column(name = "prizes_enabled")
    private Boolean prizesEnabled;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idEstablishment" }, allowSetters = true)
    private Interfacing idInterfacing;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "idManufacturer", "idFormula", "idModels" }, allowSetters = true)
    private Model idModel;

    @ManyToOne(optional = false)
    @NotNull
    private DeviceCategory idDeviceCategory;

    @ManyToOne(optional = false)
    @NotNull
    private DeviceType idDeviceType;

    @ManyToOne
    private Formula idFormulaHandpay;

    @ManyToOne
    private Formula idFormulaJackpot;

    @ManyToOne
    private Island idIsland;

    @ManyToOne
    private CurrencyType idCurrencyType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Device id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSerial() {
        return this.serial;
    }

    public Device serial(String serial) {
        this.setSerial(serial);
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Boolean getIsProtocolEsdcs() {
        return this.isProtocolEsdcs;
    }

    public Device isProtocolEsdcs(Boolean isProtocolEsdcs) {
        this.setIsProtocolEsdcs(isProtocolEsdcs);
        return this;
    }

    public void setIsProtocolEsdcs(Boolean isProtocolEsdcs) {
        this.isProtocolEsdcs = isProtocolEsdcs;
    }

    public Integer getNumberPlayedReport() {
        return this.numberPlayedReport;
    }

    public Device numberPlayedReport(Integer numberPlayedReport) {
        this.setNumberPlayedReport(numberPlayedReport);
        return this;
    }

    public void setNumberPlayedReport(Integer numberPlayedReport) {
        this.numberPlayedReport = numberPlayedReport;
    }

    public BigDecimal getSasDenomination() {
        return this.sasDenomination;
    }

    public Device sasDenomination(BigDecimal sasDenomination) {
        this.setSasDenomination(sasDenomination);
        return this;
    }

    public void setSasDenomination(BigDecimal sasDenomination) {
        this.sasDenomination = sasDenomination;
    }

    public Boolean getIsMultigame() {
        return this.isMultigame;
    }

    public Device isMultigame(Boolean isMultigame) {
        this.setIsMultigame(isMultigame);
        return this;
    }

    public void setIsMultigame(Boolean isMultigame) {
        this.isMultigame = isMultigame;
    }

    public Boolean getIsMultiDenomination() {
        return this.isMultiDenomination;
    }

    public Device isMultiDenomination(Boolean isMultiDenomination) {
        this.setIsMultiDenomination(isMultiDenomination);
        return this;
    }

    public void setIsMultiDenomination(Boolean isMultiDenomination) {
        this.isMultiDenomination = isMultiDenomination;
    }

    public Boolean getIsRetanqueo() {
        return this.isRetanqueo;
    }

    public Device isRetanqueo(Boolean isRetanqueo) {
        this.setIsRetanqueo(isRetanqueo);
        return this;
    }

    public void setIsRetanqueo(Boolean isRetanqueo) {
        this.isRetanqueo = isRetanqueo;
    }

    public String getState() {
        return this.state;
    }

    public Device state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTheoreticalHold() {
        return this.theoreticalHold;
    }

    public Device theoreticalHold(BigDecimal theoreticalHold) {
        this.setTheoreticalHold(theoreticalHold);
        return this;
    }

    public void setTheoreticalHold(BigDecimal theoreticalHold) {
        this.theoreticalHold = theoreticalHold;
    }

    public Integer getSasIdentifier() {
        return this.sasIdentifier;
    }

    public Device sasIdentifier(Integer sasIdentifier) {
        this.setSasIdentifier(sasIdentifier);
        return this;
    }

    public void setSasIdentifier(Integer sasIdentifier) {
        this.sasIdentifier = sasIdentifier;
    }

    public Long getCreditLimit() {
        return this.creditLimit;
    }

    public Device creditLimit(Long creditLimit) {
        this.setCreditLimit(creditLimit);
        return this;
    }

    public void setCreditLimit(Long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Boolean getHasHooper() {
        return this.hasHooper;
    }

    public Device hasHooper(Boolean hasHooper) {
        this.setHasHooper(hasHooper);
        return this;
    }

    public void setHasHooper(Boolean hasHooper) {
        this.hasHooper = hasHooper;
    }

    public String getColjuegosCode() {
        return this.coljuegosCode;
    }

    public Device coljuegosCode(String coljuegosCode) {
        this.setColjuegosCode(coljuegosCode);
        return this;
    }

    public void setColjuegosCode(String coljuegosCode) {
        this.coljuegosCode = coljuegosCode;
    }

    public LocalDate getFabricationDate() {
        return this.fabricationDate;
    }

    public Device fabricationDate(LocalDate fabricationDate) {
        this.setFabricationDate(fabricationDate);
        return this;
    }

    public void setFabricationDate(LocalDate fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public BigDecimal getCurrentToken() {
        return this.currentToken;
    }

    public Device currentToken(BigDecimal currentToken) {
        this.setCurrentToken(currentToken);
        return this;
    }

    public void setCurrentToken(BigDecimal currentToken) {
        this.currentToken = currentToken;
    }

    public BigDecimal getDenominationTito() {
        return this.denominationTito;
    }

    public Device denominationTito(BigDecimal denominationTito) {
        this.setDenominationTito(denominationTito);
        return this;
    }

    public void setDenominationTito(BigDecimal denominationTito) {
        this.denominationTito = denominationTito;
    }

    public ZonedDateTime getEndLostCommunication() {
        return this.endLostCommunication;
    }

    public Device endLostCommunication(ZonedDateTime endLostCommunication) {
        this.setEndLostCommunication(endLostCommunication);
        return this;
    }

    public void setEndLostCommunication(ZonedDateTime endLostCommunication) {
        this.endLostCommunication = endLostCommunication;
    }

    public ZonedDateTime getStartLostCommunication() {
        return this.startLostCommunication;
    }

    public Device startLostCommunication(ZonedDateTime startLostCommunication) {
        this.setStartLostCommunication(startLostCommunication);
        return this;
    }

    public void setStartLostCommunication(ZonedDateTime startLostCommunication) {
        this.startLostCommunication = startLostCommunication;
    }

    public BigDecimal getReportMultiplier() {
        return this.reportMultiplier;
    }

    public Device reportMultiplier(BigDecimal reportMultiplier) {
        this.setReportMultiplier(reportMultiplier);
        return this;
    }

    public void setReportMultiplier(BigDecimal reportMultiplier) {
        this.reportMultiplier = reportMultiplier;
    }

    public String getNuid() {
        return this.nuid;
    }

    public Device nuid(String nuid) {
        this.setNuid(nuid);
        return this;
    }

    public void setNuid(String nuid) {
        this.nuid = nuid;
    }

    public Boolean getPayManualPrize() {
        return this.payManualPrize;
    }

    public Device payManualPrize(Boolean payManualPrize) {
        this.setPayManualPrize(payManualPrize);
        return this;
    }

    public void setPayManualPrize(Boolean payManualPrize) {
        this.payManualPrize = payManualPrize;
    }

    public Boolean getManualHandpay() {
        return this.manualHandpay;
    }

    public Device manualHandpay(Boolean manualHandpay) {
        this.setManualHandpay(manualHandpay);
        return this;
    }

    public void setManualHandpay(Boolean manualHandpay) {
        this.manualHandpay = manualHandpay;
    }

    public Boolean getManualJackpot() {
        return this.manualJackpot;
    }

    public Device manualJackpot(Boolean manualJackpot) {
        this.setManualJackpot(manualJackpot);
        return this;
    }

    public void setManualJackpot(Boolean manualJackpot) {
        this.manualJackpot = manualJackpot;
    }

    public Boolean getManualGameEvent() {
        return this.manualGameEvent;
    }

    public Device manualGameEvent(Boolean manualGameEvent) {
        this.setManualGameEvent(manualGameEvent);
        return this;
    }

    public void setManualGameEvent(Boolean manualGameEvent) {
        this.manualGameEvent = manualGameEvent;
    }

    public String getBetCode() {
        return this.betCode;
    }

    public Device betCode(String betCode) {
        this.setBetCode(betCode);
        return this;
    }

    public void setBetCode(String betCode) {
        this.betCode = betCode;
    }

    public Boolean getHomologationIndicator() {
        return this.homologationIndicator;
    }

    public Device homologationIndicator(Boolean homologationIndicator) {
        this.setHomologationIndicator(homologationIndicator);
        return this;
    }

    public void setHomologationIndicator(Boolean homologationIndicator) {
        this.homologationIndicator = homologationIndicator;
    }

    public String getColjuegosModel() {
        return this.coljuegosModel;
    }

    public Device coljuegosModel(String coljuegosModel) {
        this.setColjuegosModel(coljuegosModel);
        return this;
    }

    public void setColjuegosModel(String coljuegosModel) {
        this.coljuegosModel = coljuegosModel;
    }

    public Boolean getReportable() {
        return this.reportable;
    }

    public Device reportable(Boolean reportable) {
        this.setReportable(reportable);
        return this;
    }

    public void setReportable(Boolean reportable) {
        this.reportable = reportable;
    }

    public BigDecimal getAftDenomination() {
        return this.aftDenomination;
    }

    public Device aftDenomination(BigDecimal aftDenomination) {
        this.setAftDenomination(aftDenomination);
        return this;
    }

    public void setAftDenomination(BigDecimal aftDenomination) {
        this.aftDenomination = aftDenomination;
    }

    public ZonedDateTime getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public Device lastUpdateDate(ZonedDateTime lastUpdateDate) {
        this.setLastUpdateDate(lastUpdateDate);
        return this;
    }

    public void setLastUpdateDate(ZonedDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean getEnableRollover() {
        return this.enableRollover;
    }

    public Device enableRollover(Boolean enableRollover) {
        this.setEnableRollover(enableRollover);
        return this;
    }

    public void setEnableRollover(Boolean enableRollover) {
        this.enableRollover = enableRollover;
    }

    public ZonedDateTime getLastCorruptionDate() {
        return this.lastCorruptionDate;
    }

    public Device lastCorruptionDate(ZonedDateTime lastCorruptionDate) {
        this.setLastCorruptionDate(lastCorruptionDate);
        return this;
    }

    public void setLastCorruptionDate(ZonedDateTime lastCorruptionDate) {
        this.lastCorruptionDate = lastCorruptionDate;
    }

    public BigDecimal getDaftDenomination() {
        return this.daftDenomination;
    }

    public Device daftDenomination(BigDecimal daftDenomination) {
        this.setDaftDenomination(daftDenomination);
        return this;
    }

    public void setDaftDenomination(BigDecimal daftDenomination) {
        this.daftDenomination = daftDenomination;
    }

    public Boolean getPrizesEnabled() {
        return this.prizesEnabled;
    }

    public Device prizesEnabled(Boolean prizesEnabled) {
        this.setPrizesEnabled(prizesEnabled);
        return this;
    }

    public void setPrizesEnabled(Boolean prizesEnabled) {
        this.prizesEnabled = prizesEnabled;
    }

    public Interfacing getIdInterfacing() {
        return this.idInterfacing;
    }

    public void setIdInterfacing(Interfacing interfacing) {
        this.idInterfacing = interfacing;
    }

    public Device idInterfacing(Interfacing interfacing) {
        this.setIdInterfacing(interfacing);
        return this;
    }

    public Model getIdModel() {
        return this.idModel;
    }

    public void setIdModel(Model model) {
        this.idModel = model;
    }

    public Device idModel(Model model) {
        this.setIdModel(model);
        return this;
    }

    public DeviceCategory getIdDeviceCategory() {
        return this.idDeviceCategory;
    }

    public void setIdDeviceCategory(DeviceCategory deviceCategory) {
        this.idDeviceCategory = deviceCategory;
    }

    public Device idDeviceCategory(DeviceCategory deviceCategory) {
        this.setIdDeviceCategory(deviceCategory);
        return this;
    }

    public DeviceType getIdDeviceType() {
        return this.idDeviceType;
    }

    public void setIdDeviceType(DeviceType deviceType) {
        this.idDeviceType = deviceType;
    }

    public Device idDeviceType(DeviceType deviceType) {
        this.setIdDeviceType(deviceType);
        return this;
    }

    public Formula getIdFormulaHandpay() {
        return this.idFormulaHandpay;
    }

    public void setIdFormulaHandpay(Formula formula) {
        this.idFormulaHandpay = formula;
    }

    public Device idFormulaHandpay(Formula formula) {
        this.setIdFormulaHandpay(formula);
        return this;
    }

    public Formula getIdFormulaJackpot() {
        return this.idFormulaJackpot;
    }

    public void setIdFormulaJackpot(Formula formula) {
        this.idFormulaJackpot = formula;
    }

    public Device idFormulaJackpot(Formula formula) {
        this.setIdFormulaJackpot(formula);
        return this;
    }

    public Island getIdIsland() {
        return this.idIsland;
    }

    public void setIdIsland(Island island) {
        this.idIsland = island;
    }

    public Device idIsland(Island island) {
        this.setIdIsland(island);
        return this;
    }

    public CurrencyType getIdCurrencyType() {
        return this.idCurrencyType;
    }

    public void setIdCurrencyType(CurrencyType currencyType) {
        this.idCurrencyType = currencyType;
    }

    public Device idCurrencyType(CurrencyType currencyType) {
        this.setIdCurrencyType(currencyType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", serial='" + getSerial() + "'" +
            ", isProtocolEsdcs='" + getIsProtocolEsdcs() + "'" +
            ", numberPlayedReport=" + getNumberPlayedReport() +
            ", sasDenomination=" + getSasDenomination() +
            ", isMultigame='" + getIsMultigame() + "'" +
            ", isMultiDenomination='" + getIsMultiDenomination() + "'" +
            ", isRetanqueo='" + getIsRetanqueo() + "'" +
            ", state='" + getState() + "'" +
            ", theoreticalHold=" + getTheoreticalHold() +
            ", sasIdentifier=" + getSasIdentifier() +
            ", creditLimit=" + getCreditLimit() +
            ", hasHooper='" + getHasHooper() + "'" +
            ", coljuegosCode='" + getColjuegosCode() + "'" +
            ", fabricationDate='" + getFabricationDate() + "'" +
            ", currentToken=" + getCurrentToken() +
            ", denominationTito=" + getDenominationTito() +
            ", endLostCommunication='" + getEndLostCommunication() + "'" +
            ", startLostCommunication='" + getStartLostCommunication() + "'" +
            ", reportMultiplier=" + getReportMultiplier() +
            ", nuid='" + getNuid() + "'" +
            ", payManualPrize='" + getPayManualPrize() + "'" +
            ", manualHandpay='" + getManualHandpay() + "'" +
            ", manualJackpot='" + getManualJackpot() + "'" +
            ", manualGameEvent='" + getManualGameEvent() + "'" +
            ", betCode='" + getBetCode() + "'" +
            ", homologationIndicator='" + getHomologationIndicator() + "'" +
            ", coljuegosModel='" + getColjuegosModel() + "'" +
            ", reportable='" + getReportable() + "'" +
            ", aftDenomination=" + getAftDenomination() +
            ", lastUpdateDate='" + getLastUpdateDate() + "'" +
            ", enableRollover='" + getEnableRollover() + "'" +
            ", lastCorruptionDate='" + getLastCorruptionDate() + "'" +
            ", daftDenomination=" + getDaftDenomination() +
            ", prizesEnabled='" + getPrizesEnabled() + "'" +
            "}";
    }
}
