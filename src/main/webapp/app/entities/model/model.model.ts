import { IManufacturer } from 'app/entities/manufacturer/manufacturer.model';
import { IFormula } from 'app/entities/formula/formula.model';
import { IEventType } from 'app/entities/event-type/event-type.model';

export interface IModel {
  id: number;
  code?: string | null;
  name?: string | null;
  subtractBonus?: boolean | null;
  collectionCeil?: number | null;
  rolloverLimit?: number | null;
  idManufacturer?: Pick<IManufacturer, 'id'> | null;
  idFormula?: Pick<IFormula, 'id'> | null;
  idModels?: Pick<IEventType, 'id'>[] | null;
}

export type NewModel = Omit<IModel, 'id'> & { id: null };
