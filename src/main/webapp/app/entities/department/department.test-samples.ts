import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: 39095,
};

export const sampleWithPartialData: IDepartment = {
  id: 42882,
  code: 'hack',
  daneCode: 'emulación',
  phoneId: 'deposit',
};

export const sampleWithFullData: IDepartment = {
  id: 45886,
  code: 'Genérico',
  name: 'compressing cross-media digital',
  daneCode: 'XML best-of-breed',
  phoneId: 'Metal',
};

export const sampleWithNewData: NewDepartment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
