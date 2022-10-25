import dayjs from 'dayjs/esm';
import { IEstablishment } from 'app/entities/establishment/establishment.model';

export interface IDeviceEstablishment {
  id: number;
  registrationAt?: dayjs.Dayjs | null;
  serial?: string | null;
  departureAt?: dayjs.Dayjs | null;
  deviceNumber?: number | null;
  consecutiveDevice?: number | null;
  negativeAward?: number | null;
  idEstablishment?: Pick<IEstablishment, 'id'> | null;
}

export type NewDeviceEstablishment = Omit<IDeviceEstablishment, 'id'> & { id: null };
