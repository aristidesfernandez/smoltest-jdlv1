import dayjs from 'dayjs/esm';

import { IDeviceEstablishment, NewDeviceEstablishment } from './device-establishment.model';

export const sampleWithRequiredData: IDeviceEstablishment = {
  id: 34811,
  registrationAt: dayjs('2021-09-26T20:17'),
  serial: 'bandwidth',
};

export const sampleWithPartialData: IDeviceEstablishment = {
  id: 93633,
  registrationAt: dayjs('2021-09-27T18:31'),
  serial: 'migración transition Heredado',
  departureAt: dayjs('2021-09-27T10:47'),
  negativeAward: 77050,
};

export const sampleWithFullData: IDeviceEstablishment = {
  id: 52319,
  registrationAt: dayjs('2021-09-26T20:16'),
  serial: 'microchip Parque transmit',
  departureAt: dayjs('2021-09-27T05:52'),
  deviceNumber: 5878,
  consecutiveDevice: 40137,
  negativeAward: 28203,
};

export const sampleWithNewData: NewDeviceEstablishment = {
  registrationAt: dayjs('2021-09-27T16:33'),
  serial: 'aprovechar Guapa Violeta',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
