import { ICounterDevice, NewCounterDevice } from './counter-device.model';

export const sampleWithRequiredData: ICounterDevice = {
  id: 39021,
};

export const sampleWithPartialData: ICounterDevice = {
  id: 39876,
  value: 83040,
};

export const sampleWithFullData: ICounterDevice = {
  id: 7076,
  value: 83078,
  rolloverValue: 54105,
  creditSale: 82236,
  manualCounter: true,
  manualMultiplier: 85052,
  decimalsManualCounter: false,
};

export const sampleWithNewData: NewCounterDevice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
