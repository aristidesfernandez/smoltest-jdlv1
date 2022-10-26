import dayjs from 'dayjs/esm';

import { EstablishmentType } from 'app/entities/enumerations/establishment-type.model';

import { IEstablishment, NewEstablishment } from './establishment.model';

export const sampleWithRequiredData: IEstablishment = {
  id: 60820,
};

export const sampleWithPartialData: IEstablishment = {
  id: 62748,
  name: 'Ergonómico',
  address: 'Chalet Productor Buckinghamshire',
  coljuegosCode: 'radical invoice',
  closeTime: dayjs('2021-09-27T07:32'),
  latitude: 34164,
  mercantileRegistration: 'Global deposit',
};

export const sampleWithFullData: IEstablishment = {
  id: 49162,
  liquidationTime: dayjs('2021-09-27T01:27'),
  name: 'éxito',
  type: EstablishmentType['CASINO'],
  neighborhood: 'Asistente Rústico',
  address: 'Puerta bus',
  coljuegosCode: 'Acero Videojuegos capacitor',
  closeTime: dayjs('2021-09-27T03:20'),
  startTime: dayjs('2021-09-27T05:36'),
  activityType: 'Madera Cambridgeshire inv',
  longitude: 86716,
  latitude: 98178,
  mercantileRegistration: 'Castilla-La',
};

export const sampleWithNewData: NewEstablishment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
