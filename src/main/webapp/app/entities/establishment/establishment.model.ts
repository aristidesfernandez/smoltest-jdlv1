import dayjs from 'dayjs/esm';
import { IOperator } from 'app/entities/operator/operator.model';
import { EstablishmentType } from 'app/entities/enumerations/establishment-type.model';

export interface IEstablishment {
  id: number;
  liquidationTime?: dayjs.Dayjs | null;
  name?: string | null;
  type?: EstablishmentType | null;
  municipalityCode?: string | null;
  neighborhood?: string | null;
  address?: string | null;
  coljuegosCode?: string | null;
  closeTime?: dayjs.Dayjs | null;
  startTime?: dayjs.Dayjs | null;
  activityType?: string | null;
  longitude?: number | null;
  latitude?: number | null;
  mercantileRegistration?: string | null;
  idOperator?: Pick<IOperator, 'id'> | null;
}

export type NewEstablishment = Omit<IEstablishment, 'id'> & { id: null };
