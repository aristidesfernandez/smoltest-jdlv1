import { ICounterEvent, NewCounterEvent } from './counter-event.model';

export const sampleWithRequiredData: ICounterEvent = {
  id: 91218,
};

export const sampleWithPartialData: ICounterEvent = {
  id: 79473,
  denominationSale: 45864,
};

export const sampleWithFullData: ICounterEvent = {
  id: 47052,
  valueCounter: 73717,
  denominationSale: 26136,
};

export const sampleWithNewData: NewCounterEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
