import { ICounterType, NewCounterType } from './counter-type.model';

export const sampleWithRequiredData: ICounterType = {
  id: 11892,
  counterCode: 'firewall primary Mesa',
};

export const sampleWithPartialData: ICounterType = {
  id: 93745,
  counterCode: 'parsing copying bandwidth',
  name: 'Salud back-end',
  description: 'withdrawal matrix',
  udteWaitTime: 36517,
};

export const sampleWithFullData: ICounterType = {
  id: 97059,
  counterCode: 'Práctico',
  name: 'Salchichas cross-platform multitarea',
  description: 'Papelería Inteligente',
  includedInFormula: false,
  prize: true,
  category: 'Rústico Corporativo',
  udteWaitTime: 37350,
};

export const sampleWithNewData: NewCounterType = {
  counterCode: 'Account',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
