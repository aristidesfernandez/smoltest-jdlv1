import { IOperationalPropertiesEstablishment, NewOperationalPropertiesEstablishment } from './operational-properties-establishment.model';

export const sampleWithRequiredData: IOperationalPropertiesEstablishment = {
  id: 48879,
};

export const sampleWithPartialData: IOperationalPropertiesEstablishment = {
  id: 44972,
  key: 81707,
};

export const sampleWithFullData: IOperationalPropertiesEstablishment = {
  id: 93098,
  value: 'Datos',
  key: 75640,
};

export const sampleWithNewData: NewOperationalPropertiesEstablishment = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
