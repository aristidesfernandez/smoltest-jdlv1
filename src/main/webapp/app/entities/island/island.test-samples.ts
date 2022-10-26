import { IIsland, NewIsland } from './island.model';

export const sampleWithRequiredData: IIsland = {
  id: 15796,
};

export const sampleWithPartialData: IIsland = {
  id: 53330,
};

export const sampleWithFullData: IIsland = {
  id: 72582,
  description: 'Sector programming',
  name: 'withdrawal hibrida Intuitivo',
};

export const sampleWithNewData: NewIsland = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
