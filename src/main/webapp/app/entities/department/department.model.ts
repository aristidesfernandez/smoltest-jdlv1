import { ICountry } from 'app/entities/country/country.model';

export interface IDepartment {
  id: number;
  code?: string | null;
  name?: string | null;
  daneCode?: string | null;
  phoneId?: string | null;
  country?: Pick<ICountry, 'id'> | null;
}

export type NewDepartment = Omit<IDepartment, 'id'> & { id: null };
