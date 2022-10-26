import { ICounterType, NewCounterType } from './counter-type.model';

export const sampleWithRequiredData: ICounterType = {
  counterCode: '1fb8bc15-1896-4edc-b45f-ecdf5d56b145',
};

export const sampleWithPartialData: ICounterType = {
  counterCode: '9a49bf25-f217-43c1-8bee-d694a580c413',
  description: 'a Rústico',
  includedInFormula: true,
  prize: false,
};

export const sampleWithFullData: ICounterType = {
  counterCode: '55195ed9-6d88-4cda-9d8c-778d739f98e7',
  name: 'Guapa Director',
  description: 'definición Organizado',
  includedInFormula: false,
  prize: true,
  category: 'Negro Fiji',
  udteWaitTime: 11807,
};

export const sampleWithNewData: NewCounterType = {
  counterCode: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
