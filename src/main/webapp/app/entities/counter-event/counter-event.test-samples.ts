import { ICounterEvent, NewCounterEvent } from './counter-event.model';

export const sampleWithRequiredData: ICounterEvent = {
  id: 'e5ec77b4-4583-4624-b6f7-a80189da1543',
};

export const sampleWithPartialData: ICounterEvent = {
  id: 'c0502d1b-fa9b-4ede-adad-ba0775a9b03b',
  denominationSale: 49891,
};

export const sampleWithFullData: ICounterEvent = {
  id: '75f8ace5-494f-4a3a-b262-4f967fac48d4',
  valueCounter: 17718,
  denominationSale: 26906,
};

export const sampleWithNewData: NewCounterEvent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
