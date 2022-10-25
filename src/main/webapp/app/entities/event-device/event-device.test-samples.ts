import dayjs from 'dayjs/esm';

import { IEventDevice, NewEventDevice } from './event-device.model';

export const sampleWithRequiredData: IEventDevice = {
  id: 75075,
};

export const sampleWithPartialData: IEventDevice = {
  id: 18526,
  createdAt: dayjs('2021-09-27T04:24'),
  moneyDenomination: 50854,
};

export const sampleWithFullData: IEventDevice = {
  id: 87580,
  createdAt: dayjs('2021-09-27T04:11'),
  theoreticalPercentage: false,
  moneyDenomination: 95357,
};

export const sampleWithNewData: NewEventDevice = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
