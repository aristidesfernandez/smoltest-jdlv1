import dayjs from 'dayjs/esm';

import { IDevice, NewDevice } from './device.model';

export const sampleWithRequiredData: IDevice = {
  id: 67542,
  serial: 'Account',
};

export const sampleWithPartialData: IDevice = {
  id: 11352,
  serial: 'digital Marketing',
  numberPlayedReport: 97804,
  sasDenomination: 29817,
  isMultiDenomination: true,
  state: 'secundaria',
  theoreticalHold: 47444,
  sasIdentifier: 75189,
  currentToken: 34451,
  denominationTito: 81214,
  startLostCommunication: dayjs('2021-09-27T16:55'),
  manualHandpay: true,
  manualJackpot: false,
  betCode: 'redundant bus Valenciana',
  homologationIndicator: false,
  coljuegosModel: 'Borders sticky',
  aftDenomination: 30393,
  lastUpdateDate: dayjs('2021-09-27T13:22'),
  lastCorruptionDate: dayjs('2021-09-27T12:36'),
};

export const sampleWithFullData: IDevice = {
  id: 83664,
  serial: 'deposit Joyería',
  isProtocolEsdcs: true,
  numberPlayedReport: 93547,
  sasDenomination: 85004,
  isMultigame: false,
  isMultiDenomination: false,
  isRetanqueo: false,
  state: 'evolve',
  theoreticalHold: 48765,
  sasIdentifier: 13198,
  creditLimit: 10830,
  hasHooper: true,
  coljuegosCode: 'Hormigon',
  fabricationDate: dayjs('2021-09-27'),
  currentToken: 33193,
  denominationTito: 39602,
  endLostCommunication: dayjs('2021-09-27T15:56'),
  startLostCommunication: dayjs('2021-09-27T16:20'),
  reportMultiplier: 83766,
  nuid: 'amplio encriptar Parafarmacia',
  payManualPrize: true,
  manualHandpay: true,
  manualJackpot: false,
  manualGameEvent: true,
  betCode: 'HDD',
  homologationIndicator: false,
  coljuegosModel: 'nueva Plástico Genérico',
  reportable: true,
  aftDenomination: 3930,
  lastUpdateDate: dayjs('2021-09-27T01:08'),
  enableRollover: false,
  lastCorruptionDate: dayjs('2021-09-26T22:01'),
  daftDenomination: 79748,
  prizesEnabled: true,
};

export const sampleWithNewData: NewDevice = {
  serial: 'Azul',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
