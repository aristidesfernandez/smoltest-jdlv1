import { IDepartment } from 'app/entities/department/department.model';

export interface IMunicipality {
  id: number;
  code?: string | null;
  name?: string | null;
  daneCode?: string | null;
  department?: Pick<IDepartment, 'id'> | null;
}

export type NewMunicipality = Omit<IMunicipality, 'id'> & { id: null };
