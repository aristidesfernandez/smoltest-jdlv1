import dayjs from 'dayjs/esm';

import { IDevice, NewDevice } from './device.model';

export const sampleWithRequiredData: IDevice = {
  id: 'a39fe1a8-1d4c-48c3-8769-909544f92fcd',
  serial: 'quantifying Integración',
};

export const sampleWithPartialData: IDevice = {
  id: '57835374-44d8-4a0e-84ab-ed4543615721',
  serial: 'red withdrawal orchestrate',
  sasDenomination: 83766,
  isMultiDenomination: true,
  isRetanqueo: false,
  sasIdentifier: 40072,
  hasHooper: false,
  currentToken: 31137,
  denominationTito: 32386,
  startLostCommunication: dayjs('2021-09-26T22:44'),
  nuid: 'Krone',
  manualHandpay: true,
  manualGameEvent: false,
  betCode: 'Morado enable nueva',
  coljuegosModel: 'codificar',
  reportable: true,
  aftDenomination: 53841,
  enableRollover: true,
  lastCorruptionDate: dayjs('2021-09-27T18:58'),
  daftDenomination: 78225,
};

export const sampleWithFullData: IDevice = {
  id: '1ec83337-931d-4f8f-b776-e61730e563e1',
  serial: 'Inteligente',
  isProtocolEsdcs: true,
  numberPlayedReport: 64197,
  sasDenomination: 1064,
  isMultigame: true,
  isMultiDenomination: true,
  isRetanqueo: false,
  state: 'transform Administrador',
  theoreticalHold: 25075,
  sasIdentifier: 85200,
  creditLimit: 34301,
  hasHooper: true,
  coljuegosCode: 'Borders',
  fabricationDate: dayjs('2021-09-27'),
  currentToken: 84444,
  denominationTito: 10544,
  endLostCommunication: dayjs('2021-09-27T00:26'),
  startLostCommunication: dayjs('2021-09-26T20:36'),
  reportMultiplier: 27894,
  nuid: 'Soluciones',
  payManualPrize: false,
  manualHandpay: true,
  manualJackpot: true,
  manualGameEvent: false,
  betCode: 'Funcionario web-readiness',
  homologationIndicator: true,
  coljuegosModel: 'monitorizada Muelle bypass',
  reportable: true,
  aftDenomination: 52005,
  lastUpdateDate: dayjs('2021-09-26T20:27'),
  enableRollover: false,
  lastCorruptionDate: dayjs('2021-09-27T18:01'),
  daftDenomination: 88304,
  prizesEnabled: false,
};

export const sampleWithNewData: NewDevice = {
  serial: 'copy Malasia middleware',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
