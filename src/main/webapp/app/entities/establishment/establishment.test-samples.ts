import dayjs from 'dayjs/esm';

import { EstablishmentType } from 'app/entities/enumerations/establishment-type.model';

import { IEstablishment, NewEstablishment } from './establishment.model';

export const sampleWithRequiredData: IEstablishment = {
  id: 60820,
};

export const sampleWithPartialData: IEstablishment = {
  id: 14633,
  name: 'Guapa',
  neighborhood: 'generación back',
  address: 'homogénea hack Relacciones',
  coljuegosCode: 'Togo bandwidth',
  longitude: 26689,
  latitude: 49162,
  mercantileRegistration: 'Plástico Savings Asistente',
};

export const sampleWithFullData: IEstablishment = {
  id: 8771,
  liquidationTime: dayjs('2021-09-27T16:15'),
  name: 'withdrawal wireless payment',
  type: EstablishmentType['RUTA'],
  municipalityCode: 'real-time',
  neighborhood: 'mano',
  address: 'Oro Madera',
  coljuegosCode: 'Facilitador Futuro',
  closeTime: dayjs('2021-09-26T20:12'),
  startTime: dayjs('2021-09-27T14:28'),
  activityType: 'one-to-one Rojo',
  longitude: 18838,
  latitude: 39115,
  mercantileRegistration: 'Persistente',
};

export const sampleWithNewData: NewEstablishment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
