import { IInterfacing, NewInterfacing } from './interfacing.model';

export const sampleWithRequiredData: IInterfacing = {
  id: 12863,
};

export const sampleWithPartialData: IInterfacing = {
  id: 28442,
  isAssigned: false,
  ipAddress: 'Humano Borders negocio',
  hash: 'Money overriding',
  port: 'Sopa Account',
};

export const sampleWithFullData: IInterfacing = {
  id: 1474,
  isAssigned: false,
  ipAddress: 'e-commerce Bedfordshire',
  hash: 'Toallas Parafarmacia',
  serial: 'Configurable connecting synthesizing',
  version: 'Negro Queso Mejorado',
  port: 'synthesizing Coordinador',
};

export const sampleWithNewData: NewInterfacing = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
