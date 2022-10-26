import dayjs from 'dayjs/esm';

import { IDeviceEstablishment, NewDeviceEstablishment } from './device-establishment.model';

export const sampleWithRequiredData: IDeviceEstablishment = {
  id: '5f2b14c6-5ce0-4e5a-96e3-eb86c8fcb387',
  registrationAt: dayjs('2021-09-27T05:56'),
  serial: 'transmit Andalucía bidireccional',
};

export const sampleWithPartialData: IDeviceEstablishment = {
  id: 'dc3f54bc-d636-4aef-afaa-eb6ba3a8238b',
  registrationAt: dayjs('2021-09-27T18:43'),
  serial: 'cohesiva',
  deviceNumber: 14057,
  consecutiveDevice: 20442,
};

export const sampleWithFullData: IDeviceEstablishment = {
  id: '0e886656-b1da-4d5b-ba2a-6ba2a788e4e9',
  registrationAt: dayjs('2021-09-27T15:28'),
  serial: 'Adelante',
  departureAt: dayjs('2021-09-26T20:33'),
  deviceNumber: 3269,
  consecutiveDevice: 62359,
  negativeAward: 95339,
};

export const sampleWithNewData: NewDeviceEstablishment = {
  registrationAt: dayjs('2021-09-27T16:09'),
  serial: 'Parafarmacia',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
