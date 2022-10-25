package co.com.ies.smol.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link co.com.ies.smol.domain.Device} entity. This class is used
 * in {@link co.com.ies.smol.web.rest.DeviceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /devices?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeviceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter serial;

    private BooleanFilter isProtocolEsdcs;

    private IntegerFilter numberPlayedReport;

    private BigDecimalFilter sasDenomination;

    private BooleanFilter isMultigame;

    private BooleanFilter isMultiDenomination;

    private BooleanFilter isRetanqueo;

    private StringFilter state;

    private BigDecimalFilter theoreticalHold;

    private IntegerFilter sasIdentifier;

    private LongFilter creditLimit;

    private BooleanFilter hasHooper;

    private StringFilter coljuegosCode;

    private LocalDateFilter fabricationDate;

    private BigDecimalFilter currentToken;

    private BigDecimalFilter denominationTito;

    private ZonedDateTimeFilter endLostCommunication;

    private ZonedDateTimeFilter startLostCommunication;

    private BigDecimalFilter reportMultiplier;

    private StringFilter nuid;

    private BooleanFilter payManualPrize;

    private BooleanFilter manualHandpay;

    private BooleanFilter manualJackpot;

    private BooleanFilter manualGameEvent;

    private StringFilter betCode;

    private BooleanFilter homologationIndicator;

    private StringFilter coljuegosModel;

    private BooleanFilter reportable;

    private BigDecimalFilter aftDenomination;

    private ZonedDateTimeFilter lastUpdateDate;

    private BooleanFilter enableRollover;

    private ZonedDateTimeFilter lastCorruptionDate;

    private BigDecimalFilter daftDenomination;

    private BooleanFilter prizesEnabled;

    private LongFilter idInterfacingId;

    private LongFilter idModelId;

    private LongFilter idDeviceCategoryId;

    private LongFilter idDeviceTypeId;

    private LongFilter idFormulaHandpayId;

    private LongFilter idFormulaJackpotId;

    private LongFilter idIslandId;

    private LongFilter idCurrencyTypeId;

    private Boolean distinct;

    public DeviceCriteria() {}

    public DeviceCriteria(DeviceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.serial = other.serial == null ? null : other.serial.copy();
        this.isProtocolEsdcs = other.isProtocolEsdcs == null ? null : other.isProtocolEsdcs.copy();
        this.numberPlayedReport = other.numberPlayedReport == null ? null : other.numberPlayedReport.copy();
        this.sasDenomination = other.sasDenomination == null ? null : other.sasDenomination.copy();
        this.isMultigame = other.isMultigame == null ? null : other.isMultigame.copy();
        this.isMultiDenomination = other.isMultiDenomination == null ? null : other.isMultiDenomination.copy();
        this.isRetanqueo = other.isRetanqueo == null ? null : other.isRetanqueo.copy();
        this.state = other.state == null ? null : other.state.copy();
        this.theoreticalHold = other.theoreticalHold == null ? null : other.theoreticalHold.copy();
        this.sasIdentifier = other.sasIdentifier == null ? null : other.sasIdentifier.copy();
        this.creditLimit = other.creditLimit == null ? null : other.creditLimit.copy();
        this.hasHooper = other.hasHooper == null ? null : other.hasHooper.copy();
        this.coljuegosCode = other.coljuegosCode == null ? null : other.coljuegosCode.copy();
        this.fabricationDate = other.fabricationDate == null ? null : other.fabricationDate.copy();
        this.currentToken = other.currentToken == null ? null : other.currentToken.copy();
        this.denominationTito = other.denominationTito == null ? null : other.denominationTito.copy();
        this.endLostCommunication = other.endLostCommunication == null ? null : other.endLostCommunication.copy();
        this.startLostCommunication = other.startLostCommunication == null ? null : other.startLostCommunication.copy();
        this.reportMultiplier = other.reportMultiplier == null ? null : other.reportMultiplier.copy();
        this.nuid = other.nuid == null ? null : other.nuid.copy();
        this.payManualPrize = other.payManualPrize == null ? null : other.payManualPrize.copy();
        this.manualHandpay = other.manualHandpay == null ? null : other.manualHandpay.copy();
        this.manualJackpot = other.manualJackpot == null ? null : other.manualJackpot.copy();
        this.manualGameEvent = other.manualGameEvent == null ? null : other.manualGameEvent.copy();
        this.betCode = other.betCode == null ? null : other.betCode.copy();
        this.homologationIndicator = other.homologationIndicator == null ? null : other.homologationIndicator.copy();
        this.coljuegosModel = other.coljuegosModel == null ? null : other.coljuegosModel.copy();
        this.reportable = other.reportable == null ? null : other.reportable.copy();
        this.aftDenomination = other.aftDenomination == null ? null : other.aftDenomination.copy();
        this.lastUpdateDate = other.lastUpdateDate == null ? null : other.lastUpdateDate.copy();
        this.enableRollover = other.enableRollover == null ? null : other.enableRollover.copy();
        this.lastCorruptionDate = other.lastCorruptionDate == null ? null : other.lastCorruptionDate.copy();
        this.daftDenomination = other.daftDenomination == null ? null : other.daftDenomination.copy();
        this.prizesEnabled = other.prizesEnabled == null ? null : other.prizesEnabled.copy();
        this.idInterfacingId = other.idInterfacingId == null ? null : other.idInterfacingId.copy();
        this.idModelId = other.idModelId == null ? null : other.idModelId.copy();
        this.idDeviceCategoryId = other.idDeviceCategoryId == null ? null : other.idDeviceCategoryId.copy();
        this.idDeviceTypeId = other.idDeviceTypeId == null ? null : other.idDeviceTypeId.copy();
        this.idFormulaHandpayId = other.idFormulaHandpayId == null ? null : other.idFormulaHandpayId.copy();
        this.idFormulaJackpotId = other.idFormulaJackpotId == null ? null : other.idFormulaJackpotId.copy();
        this.idIslandId = other.idIslandId == null ? null : other.idIslandId.copy();
        this.idCurrencyTypeId = other.idCurrencyTypeId == null ? null : other.idCurrencyTypeId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DeviceCriteria copy() {
        return new DeviceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getSerial() {
        return serial;
    }

    public StringFilter serial() {
        if (serial == null) {
            serial = new StringFilter();
        }
        return serial;
    }

    public void setSerial(StringFilter serial) {
        this.serial = serial;
    }

    public BooleanFilter getIsProtocolEsdcs() {
        return isProtocolEsdcs;
    }

    public BooleanFilter isProtocolEsdcs() {
        if (isProtocolEsdcs == null) {
            isProtocolEsdcs = new BooleanFilter();
        }
        return isProtocolEsdcs;
    }

    public void setIsProtocolEsdcs(BooleanFilter isProtocolEsdcs) {
        this.isProtocolEsdcs = isProtocolEsdcs;
    }

    public IntegerFilter getNumberPlayedReport() {
        return numberPlayedReport;
    }

    public IntegerFilter numberPlayedReport() {
        if (numberPlayedReport == null) {
            numberPlayedReport = new IntegerFilter();
        }
        return numberPlayedReport;
    }

    public void setNumberPlayedReport(IntegerFilter numberPlayedReport) {
        this.numberPlayedReport = numberPlayedReport;
    }

    public BigDecimalFilter getSasDenomination() {
        return sasDenomination;
    }

    public BigDecimalFilter sasDenomination() {
        if (sasDenomination == null) {
            sasDenomination = new BigDecimalFilter();
        }
        return sasDenomination;
    }

    public void setSasDenomination(BigDecimalFilter sasDenomination) {
        this.sasDenomination = sasDenomination;
    }

    public BooleanFilter getIsMultigame() {
        return isMultigame;
    }

    public BooleanFilter isMultigame() {
        if (isMultigame == null) {
            isMultigame = new BooleanFilter();
        }
        return isMultigame;
    }

    public void setIsMultigame(BooleanFilter isMultigame) {
        this.isMultigame = isMultigame;
    }

    public BooleanFilter getIsMultiDenomination() {
        return isMultiDenomination;
    }

    public BooleanFilter isMultiDenomination() {
        if (isMultiDenomination == null) {
            isMultiDenomination = new BooleanFilter();
        }
        return isMultiDenomination;
    }

    public void setIsMultiDenomination(BooleanFilter isMultiDenomination) {
        this.isMultiDenomination = isMultiDenomination;
    }

    public BooleanFilter getIsRetanqueo() {
        return isRetanqueo;
    }

    public BooleanFilter isRetanqueo() {
        if (isRetanqueo == null) {
            isRetanqueo = new BooleanFilter();
        }
        return isRetanqueo;
    }

    public void setIsRetanqueo(BooleanFilter isRetanqueo) {
        this.isRetanqueo = isRetanqueo;
    }

    public StringFilter getState() {
        return state;
    }

    public StringFilter state() {
        if (state == null) {
            state = new StringFilter();
        }
        return state;
    }

    public void setState(StringFilter state) {
        this.state = state;
    }

    public BigDecimalFilter getTheoreticalHold() {
        return theoreticalHold;
    }

    public BigDecimalFilter theoreticalHold() {
        if (theoreticalHold == null) {
            theoreticalHold = new BigDecimalFilter();
        }
        return theoreticalHold;
    }

    public void setTheoreticalHold(BigDecimalFilter theoreticalHold) {
        this.theoreticalHold = theoreticalHold;
    }

    public IntegerFilter getSasIdentifier() {
        return sasIdentifier;
    }

    public IntegerFilter sasIdentifier() {
        if (sasIdentifier == null) {
            sasIdentifier = new IntegerFilter();
        }
        return sasIdentifier;
    }

    public void setSasIdentifier(IntegerFilter sasIdentifier) {
        this.sasIdentifier = sasIdentifier;
    }

    public LongFilter getCreditLimit() {
        return creditLimit;
    }

    public LongFilter creditLimit() {
        if (creditLimit == null) {
            creditLimit = new LongFilter();
        }
        return creditLimit;
    }

    public void setCreditLimit(LongFilter creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BooleanFilter getHasHooper() {
        return hasHooper;
    }

    public BooleanFilter hasHooper() {
        if (hasHooper == null) {
            hasHooper = new BooleanFilter();
        }
        return hasHooper;
    }

    public void setHasHooper(BooleanFilter hasHooper) {
        this.hasHooper = hasHooper;
    }

    public StringFilter getColjuegosCode() {
        return coljuegosCode;
    }

    public StringFilter coljuegosCode() {
        if (coljuegosCode == null) {
            coljuegosCode = new StringFilter();
        }
        return coljuegosCode;
    }

    public void setColjuegosCode(StringFilter coljuegosCode) {
        this.coljuegosCode = coljuegosCode;
    }

    public LocalDateFilter getFabricationDate() {
        return fabricationDate;
    }

    public LocalDateFilter fabricationDate() {
        if (fabricationDate == null) {
            fabricationDate = new LocalDateFilter();
        }
        return fabricationDate;
    }

    public void setFabricationDate(LocalDateFilter fabricationDate) {
        this.fabricationDate = fabricationDate;
    }

    public BigDecimalFilter getCurrentToken() {
        return currentToken;
    }

    public BigDecimalFilter currentToken() {
        if (currentToken == null) {
            currentToken = new BigDecimalFilter();
        }
        return currentToken;
    }

    public void setCurrentToken(BigDecimalFilter currentToken) {
        this.currentToken = currentToken;
    }

    public BigDecimalFilter getDenominationTito() {
        return denominationTito;
    }

    public BigDecimalFilter denominationTito() {
        if (denominationTito == null) {
            denominationTito = new BigDecimalFilter();
        }
        return denominationTito;
    }

    public void setDenominationTito(BigDecimalFilter denominationTito) {
        this.denominationTito = denominationTito;
    }

    public ZonedDateTimeFilter getEndLostCommunication() {
        return endLostCommunication;
    }

    public ZonedDateTimeFilter endLostCommunication() {
        if (endLostCommunication == null) {
            endLostCommunication = new ZonedDateTimeFilter();
        }
        return endLostCommunication;
    }

    public void setEndLostCommunication(ZonedDateTimeFilter endLostCommunication) {
        this.endLostCommunication = endLostCommunication;
    }

    public ZonedDateTimeFilter getStartLostCommunication() {
        return startLostCommunication;
    }

    public ZonedDateTimeFilter startLostCommunication() {
        if (startLostCommunication == null) {
            startLostCommunication = new ZonedDateTimeFilter();
        }
        return startLostCommunication;
    }

    public void setStartLostCommunication(ZonedDateTimeFilter startLostCommunication) {
        this.startLostCommunication = startLostCommunication;
    }

    public BigDecimalFilter getReportMultiplier() {
        return reportMultiplier;
    }

    public BigDecimalFilter reportMultiplier() {
        if (reportMultiplier == null) {
            reportMultiplier = new BigDecimalFilter();
        }
        return reportMultiplier;
    }

    public void setReportMultiplier(BigDecimalFilter reportMultiplier) {
        this.reportMultiplier = reportMultiplier;
    }

    public StringFilter getNuid() {
        return nuid;
    }

    public StringFilter nuid() {
        if (nuid == null) {
            nuid = new StringFilter();
        }
        return nuid;
    }

    public void setNuid(StringFilter nuid) {
        this.nuid = nuid;
    }

    public BooleanFilter getPayManualPrize() {
        return payManualPrize;
    }

    public BooleanFilter payManualPrize() {
        if (payManualPrize == null) {
            payManualPrize = new BooleanFilter();
        }
        return payManualPrize;
    }

    public void setPayManualPrize(BooleanFilter payManualPrize) {
        this.payManualPrize = payManualPrize;
    }

    public BooleanFilter getManualHandpay() {
        return manualHandpay;
    }

    public BooleanFilter manualHandpay() {
        if (manualHandpay == null) {
            manualHandpay = new BooleanFilter();
        }
        return manualHandpay;
    }

    public void setManualHandpay(BooleanFilter manualHandpay) {
        this.manualHandpay = manualHandpay;
    }

    public BooleanFilter getManualJackpot() {
        return manualJackpot;
    }

    public BooleanFilter manualJackpot() {
        if (manualJackpot == null) {
            manualJackpot = new BooleanFilter();
        }
        return manualJackpot;
    }

    public void setManualJackpot(BooleanFilter manualJackpot) {
        this.manualJackpot = manualJackpot;
    }

    public BooleanFilter getManualGameEvent() {
        return manualGameEvent;
    }

    public BooleanFilter manualGameEvent() {
        if (manualGameEvent == null) {
            manualGameEvent = new BooleanFilter();
        }
        return manualGameEvent;
    }

    public void setManualGameEvent(BooleanFilter manualGameEvent) {
        this.manualGameEvent = manualGameEvent;
    }

    public StringFilter getBetCode() {
        return betCode;
    }

    public StringFilter betCode() {
        if (betCode == null) {
            betCode = new StringFilter();
        }
        return betCode;
    }

    public void setBetCode(StringFilter betCode) {
        this.betCode = betCode;
    }

    public BooleanFilter getHomologationIndicator() {
        return homologationIndicator;
    }

    public BooleanFilter homologationIndicator() {
        if (homologationIndicator == null) {
            homologationIndicator = new BooleanFilter();
        }
        return homologationIndicator;
    }

    public void setHomologationIndicator(BooleanFilter homologationIndicator) {
        this.homologationIndicator = homologationIndicator;
    }

    public StringFilter getColjuegosModel() {
        return coljuegosModel;
    }

    public StringFilter coljuegosModel() {
        if (coljuegosModel == null) {
            coljuegosModel = new StringFilter();
        }
        return coljuegosModel;
    }

    public void setColjuegosModel(StringFilter coljuegosModel) {
        this.coljuegosModel = coljuegosModel;
    }

    public BooleanFilter getReportable() {
        return reportable;
    }

    public BooleanFilter reportable() {
        if (reportable == null) {
            reportable = new BooleanFilter();
        }
        return reportable;
    }

    public void setReportable(BooleanFilter reportable) {
        this.reportable = reportable;
    }

    public BigDecimalFilter getAftDenomination() {
        return aftDenomination;
    }

    public BigDecimalFilter aftDenomination() {
        if (aftDenomination == null) {
            aftDenomination = new BigDecimalFilter();
        }
        return aftDenomination;
    }

    public void setAftDenomination(BigDecimalFilter aftDenomination) {
        this.aftDenomination = aftDenomination;
    }

    public ZonedDateTimeFilter getLastUpdateDate() {
        return lastUpdateDate;
    }

    public ZonedDateTimeFilter lastUpdateDate() {
        if (lastUpdateDate == null) {
            lastUpdateDate = new ZonedDateTimeFilter();
        }
        return lastUpdateDate;
    }

    public void setLastUpdateDate(ZonedDateTimeFilter lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BooleanFilter getEnableRollover() {
        return enableRollover;
    }

    public BooleanFilter enableRollover() {
        if (enableRollover == null) {
            enableRollover = new BooleanFilter();
        }
        return enableRollover;
    }

    public void setEnableRollover(BooleanFilter enableRollover) {
        this.enableRollover = enableRollover;
    }

    public ZonedDateTimeFilter getLastCorruptionDate() {
        return lastCorruptionDate;
    }

    public ZonedDateTimeFilter lastCorruptionDate() {
        if (lastCorruptionDate == null) {
            lastCorruptionDate = new ZonedDateTimeFilter();
        }
        return lastCorruptionDate;
    }

    public void setLastCorruptionDate(ZonedDateTimeFilter lastCorruptionDate) {
        this.lastCorruptionDate = lastCorruptionDate;
    }

    public BigDecimalFilter getDaftDenomination() {
        return daftDenomination;
    }

    public BigDecimalFilter daftDenomination() {
        if (daftDenomination == null) {
            daftDenomination = new BigDecimalFilter();
        }
        return daftDenomination;
    }

    public void setDaftDenomination(BigDecimalFilter daftDenomination) {
        this.daftDenomination = daftDenomination;
    }

    public BooleanFilter getPrizesEnabled() {
        return prizesEnabled;
    }

    public BooleanFilter prizesEnabled() {
        if (prizesEnabled == null) {
            prizesEnabled = new BooleanFilter();
        }
        return prizesEnabled;
    }

    public void setPrizesEnabled(BooleanFilter prizesEnabled) {
        this.prizesEnabled = prizesEnabled;
    }

    public LongFilter getIdInterfacingId() {
        return idInterfacingId;
    }

    public LongFilter idInterfacingId() {
        if (idInterfacingId == null) {
            idInterfacingId = new LongFilter();
        }
        return idInterfacingId;
    }

    public void setIdInterfacingId(LongFilter idInterfacingId) {
        this.idInterfacingId = idInterfacingId;
    }

    public LongFilter getIdModelId() {
        return idModelId;
    }

    public LongFilter idModelId() {
        if (idModelId == null) {
            idModelId = new LongFilter();
        }
        return idModelId;
    }

    public void setIdModelId(LongFilter idModelId) {
        this.idModelId = idModelId;
    }

    public LongFilter getIdDeviceCategoryId() {
        return idDeviceCategoryId;
    }

    public LongFilter idDeviceCategoryId() {
        if (idDeviceCategoryId == null) {
            idDeviceCategoryId = new LongFilter();
        }
        return idDeviceCategoryId;
    }

    public void setIdDeviceCategoryId(LongFilter idDeviceCategoryId) {
        this.idDeviceCategoryId = idDeviceCategoryId;
    }

    public LongFilter getIdDeviceTypeId() {
        return idDeviceTypeId;
    }

    public LongFilter idDeviceTypeId() {
        if (idDeviceTypeId == null) {
            idDeviceTypeId = new LongFilter();
        }
        return idDeviceTypeId;
    }

    public void setIdDeviceTypeId(LongFilter idDeviceTypeId) {
        this.idDeviceTypeId = idDeviceTypeId;
    }

    public LongFilter getIdFormulaHandpayId() {
        return idFormulaHandpayId;
    }

    public LongFilter idFormulaHandpayId() {
        if (idFormulaHandpayId == null) {
            idFormulaHandpayId = new LongFilter();
        }
        return idFormulaHandpayId;
    }

    public void setIdFormulaHandpayId(LongFilter idFormulaHandpayId) {
        this.idFormulaHandpayId = idFormulaHandpayId;
    }

    public LongFilter getIdFormulaJackpotId() {
        return idFormulaJackpotId;
    }

    public LongFilter idFormulaJackpotId() {
        if (idFormulaJackpotId == null) {
            idFormulaJackpotId = new LongFilter();
        }
        return idFormulaJackpotId;
    }

    public void setIdFormulaJackpotId(LongFilter idFormulaJackpotId) {
        this.idFormulaJackpotId = idFormulaJackpotId;
    }

    public LongFilter getIdIslandId() {
        return idIslandId;
    }

    public LongFilter idIslandId() {
        if (idIslandId == null) {
            idIslandId = new LongFilter();
        }
        return idIslandId;
    }

    public void setIdIslandId(LongFilter idIslandId) {
        this.idIslandId = idIslandId;
    }

    public LongFilter getIdCurrencyTypeId() {
        return idCurrencyTypeId;
    }

    public LongFilter idCurrencyTypeId() {
        if (idCurrencyTypeId == null) {
            idCurrencyTypeId = new LongFilter();
        }
        return idCurrencyTypeId;
    }

    public void setIdCurrencyTypeId(LongFilter idCurrencyTypeId) {
        this.idCurrencyTypeId = idCurrencyTypeId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DeviceCriteria that = (DeviceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(serial, that.serial) &&
            Objects.equals(isProtocolEsdcs, that.isProtocolEsdcs) &&
            Objects.equals(numberPlayedReport, that.numberPlayedReport) &&
            Objects.equals(sasDenomination, that.sasDenomination) &&
            Objects.equals(isMultigame, that.isMultigame) &&
            Objects.equals(isMultiDenomination, that.isMultiDenomination) &&
            Objects.equals(isRetanqueo, that.isRetanqueo) &&
            Objects.equals(state, that.state) &&
            Objects.equals(theoreticalHold, that.theoreticalHold) &&
            Objects.equals(sasIdentifier, that.sasIdentifier) &&
            Objects.equals(creditLimit, that.creditLimit) &&
            Objects.equals(hasHooper, that.hasHooper) &&
            Objects.equals(coljuegosCode, that.coljuegosCode) &&
            Objects.equals(fabricationDate, that.fabricationDate) &&
            Objects.equals(currentToken, that.currentToken) &&
            Objects.equals(denominationTito, that.denominationTito) &&
            Objects.equals(endLostCommunication, that.endLostCommunication) &&
            Objects.equals(startLostCommunication, that.startLostCommunication) &&
            Objects.equals(reportMultiplier, that.reportMultiplier) &&
            Objects.equals(nuid, that.nuid) &&
            Objects.equals(payManualPrize, that.payManualPrize) &&
            Objects.equals(manualHandpay, that.manualHandpay) &&
            Objects.equals(manualJackpot, that.manualJackpot) &&
            Objects.equals(manualGameEvent, that.manualGameEvent) &&
            Objects.equals(betCode, that.betCode) &&
            Objects.equals(homologationIndicator, that.homologationIndicator) &&
            Objects.equals(coljuegosModel, that.coljuegosModel) &&
            Objects.equals(reportable, that.reportable) &&
            Objects.equals(aftDenomination, that.aftDenomination) &&
            Objects.equals(lastUpdateDate, that.lastUpdateDate) &&
            Objects.equals(enableRollover, that.enableRollover) &&
            Objects.equals(lastCorruptionDate, that.lastCorruptionDate) &&
            Objects.equals(daftDenomination, that.daftDenomination) &&
            Objects.equals(prizesEnabled, that.prizesEnabled) &&
            Objects.equals(idInterfacingId, that.idInterfacingId) &&
            Objects.equals(idModelId, that.idModelId) &&
            Objects.equals(idDeviceCategoryId, that.idDeviceCategoryId) &&
            Objects.equals(idDeviceTypeId, that.idDeviceTypeId) &&
            Objects.equals(idFormulaHandpayId, that.idFormulaHandpayId) &&
            Objects.equals(idFormulaJackpotId, that.idFormulaJackpotId) &&
            Objects.equals(idIslandId, that.idIslandId) &&
            Objects.equals(idCurrencyTypeId, that.idCurrencyTypeId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            serial,
            isProtocolEsdcs,
            numberPlayedReport,
            sasDenomination,
            isMultigame,
            isMultiDenomination,
            isRetanqueo,
            state,
            theoreticalHold,
            sasIdentifier,
            creditLimit,
            hasHooper,
            coljuegosCode,
            fabricationDate,
            currentToken,
            denominationTito,
            endLostCommunication,
            startLostCommunication,
            reportMultiplier,
            nuid,
            payManualPrize,
            manualHandpay,
            manualJackpot,
            manualGameEvent,
            betCode,
            homologationIndicator,
            coljuegosModel,
            reportable,
            aftDenomination,
            lastUpdateDate,
            enableRollover,
            lastCorruptionDate,
            daftDenomination,
            prizesEnabled,
            idInterfacingId,
            idModelId,
            idDeviceCategoryId,
            idDeviceTypeId,
            idFormulaHandpayId,
            idFormulaJackpotId,
            idIslandId,
            idCurrencyTypeId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviceCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (serial != null ? "serial=" + serial + ", " : "") +
            (isProtocolEsdcs != null ? "isProtocolEsdcs=" + isProtocolEsdcs + ", " : "") +
            (numberPlayedReport != null ? "numberPlayedReport=" + numberPlayedReport + ", " : "") +
            (sasDenomination != null ? "sasDenomination=" + sasDenomination + ", " : "") +
            (isMultigame != null ? "isMultigame=" + isMultigame + ", " : "") +
            (isMultiDenomination != null ? "isMultiDenomination=" + isMultiDenomination + ", " : "") +
            (isRetanqueo != null ? "isRetanqueo=" + isRetanqueo + ", " : "") +
            (state != null ? "state=" + state + ", " : "") +
            (theoreticalHold != null ? "theoreticalHold=" + theoreticalHold + ", " : "") +
            (sasIdentifier != null ? "sasIdentifier=" + sasIdentifier + ", " : "") +
            (creditLimit != null ? "creditLimit=" + creditLimit + ", " : "") +
            (hasHooper != null ? "hasHooper=" + hasHooper + ", " : "") +
            (coljuegosCode != null ? "coljuegosCode=" + coljuegosCode + ", " : "") +
            (fabricationDate != null ? "fabricationDate=" + fabricationDate + ", " : "") +
            (currentToken != null ? "currentToken=" + currentToken + ", " : "") +
            (denominationTito != null ? "denominationTito=" + denominationTito + ", " : "") +
            (endLostCommunication != null ? "endLostCommunication=" + endLostCommunication + ", " : "") +
            (startLostCommunication != null ? "startLostCommunication=" + startLostCommunication + ", " : "") +
            (reportMultiplier != null ? "reportMultiplier=" + reportMultiplier + ", " : "") +
            (nuid != null ? "nuid=" + nuid + ", " : "") +
            (payManualPrize != null ? "payManualPrize=" + payManualPrize + ", " : "") +
            (manualHandpay != null ? "manualHandpay=" + manualHandpay + ", " : "") +
            (manualJackpot != null ? "manualJackpot=" + manualJackpot + ", " : "") +
            (manualGameEvent != null ? "manualGameEvent=" + manualGameEvent + ", " : "") +
            (betCode != null ? "betCode=" + betCode + ", " : "") +
            (homologationIndicator != null ? "homologationIndicator=" + homologationIndicator + ", " : "") +
            (coljuegosModel != null ? "coljuegosModel=" + coljuegosModel + ", " : "") +
            (reportable != null ? "reportable=" + reportable + ", " : "") +
            (aftDenomination != null ? "aftDenomination=" + aftDenomination + ", " : "") +
            (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "") +
            (enableRollover != null ? "enableRollover=" + enableRollover + ", " : "") +
            (lastCorruptionDate != null ? "lastCorruptionDate=" + lastCorruptionDate + ", " : "") +
            (daftDenomination != null ? "daftDenomination=" + daftDenomination + ", " : "") +
            (prizesEnabled != null ? "prizesEnabled=" + prizesEnabled + ", " : "") +
            (idInterfacingId != null ? "idInterfacingId=" + idInterfacingId + ", " : "") +
            (idModelId != null ? "idModelId=" + idModelId + ", " : "") +
            (idDeviceCategoryId != null ? "idDeviceCategoryId=" + idDeviceCategoryId + ", " : "") +
            (idDeviceTypeId != null ? "idDeviceTypeId=" + idDeviceTypeId + ", " : "") +
            (idFormulaHandpayId != null ? "idFormulaHandpayId=" + idFormulaHandpayId + ", " : "") +
            (idFormulaJackpotId != null ? "idFormulaJackpotId=" + idFormulaJackpotId + ", " : "") +
            (idIslandId != null ? "idIslandId=" + idIslandId + ", " : "") +
            (idCurrencyTypeId != null ? "idCurrencyTypeId=" + idCurrencyTypeId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
