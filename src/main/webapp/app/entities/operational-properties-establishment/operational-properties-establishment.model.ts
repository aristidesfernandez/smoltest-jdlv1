import { IEstablishment } from 'app/entities/establishment/establishment.model';

export interface IOperationalPropertiesEstablishment {
  id: number;
  value?: string | null;
  key?: number | null;
  idEstablishment?: Pick<IEstablishment, 'id'> | null;
}

export type NewOperationalPropertiesEstablishment = Omit<IOperationalPropertiesEstablishment, 'id'> & { id: null };
