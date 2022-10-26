import dayjs from 'dayjs/esm';

import { IOperator, NewOperator } from './operator.model';

export const sampleWithRequiredData: IOperator = {
  id: 23970,
};

export const sampleWithPartialData: IOperator = {
  id: 32483,
  permitDescription: 'País Account Raton',
  endDate: dayjs('2021-09-27T15:43'),
  minAccumulatedPrize: 53294,
  minTransactionAccumulated: 79240,
  nit: 'Lats',
  contractNumber: 'multimedia Coche Cataluña',
  brand: 'JBOD architectures',
};

export const sampleWithFullData: IOperator = {
  id: 29652,
  permitDescription: 'Metal indexing mano',
  endDate: dayjs('2021-09-27T15:00'),
  startDate: dayjs('2021-09-26T23:10'),
  minAccumulatedPrize: 88964,
  minIndividualPrize: 97572,
  minTransactionAccumulated: 87419,
  minIndividualTransaction: 44618,
  nit: 'Account incremental Estratega',
  contractNumber: 'Rojo',
  eventQuantity: 24886,
  companyName: 'fuerza payment payment',
  municipalityCode: 'productize',
  brand: 'Buckinghamshire Corporativo Consultor',
};

export const sampleWithNewData: NewOperator = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
