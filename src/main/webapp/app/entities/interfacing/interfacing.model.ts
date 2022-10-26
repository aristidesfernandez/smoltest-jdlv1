import { IEstablishment } from 'app/entities/establishment/establishment.model';

export interface IInterfacing {
  id: number;
  isAssigned?: boolean | null;
  ipAddress?: string | null;
  hash?: string | null;
  serial?: string | null;
  version?: string | null;
  port?: string | null;
  idEstablishment?: Pick<IEstablishment, 'id'> | null;
}

export type NewInterfacing = Omit<IInterfacing, 'id'> & { id: null };
