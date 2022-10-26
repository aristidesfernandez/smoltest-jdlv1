import { IMunicipality, NewMunicipality } from './municipality.model';

export const sampleWithRequiredData: IMunicipality = {
  id: 14326,
};

export const sampleWithPartialData: IMunicipality = {
  id: 68150,
  code: 'Avon deposit RAM',
  name: 'capacitor Auto',
  daneCode: 'Funcionario Riera Sincron',
};

export const sampleWithFullData: IMunicipality = {
  id: 44133,
  code: 'transparent Cambridgeshir',
  name: 'Account Amarillo Música',
  daneCode: 'driver TCP platforms',
};

export const sampleWithNewData: NewMunicipality = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
