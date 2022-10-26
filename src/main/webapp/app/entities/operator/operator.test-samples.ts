import dayjs from 'dayjs/esm';

import { IOperator, NewOperator } from './operator.model';

export const sampleWithRequiredData: IOperator = {
  id: 23970,
};

export const sampleWithPartialData: IOperator = {
  id: 95347,
  permitDescription: 'Intranet',
  endDate: dayjs('2021-09-27T15:03'),
  minAccumulatedPrize: 62324,
  minTransactionAccumulated: 37914,
  nit: 'Raton Gorro',
  contractNumber: 'Lats',
};

export const sampleWithFullData: IOperator = {
  id: 80524,
  permitDescription: 'invoice',
  endDate: dayjs('2021-09-27T15:47'),
  startDate: dayjs('2021-09-27T10:04'),
  minAccumulatedPrize: 4485,
  minIndividualPrize: 79066,
  minTransactionAccumulated: 55623,
  minIndividualTransaction: 47135,
  nit: 'Account deploy',
  contractNumber: 'software Naira',
  eventQuantity: 48706,
  companyName: 'mano Morado',
  brand: 'ADP Account incremental',
};

export const sampleWithNewData: NewOperator = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
