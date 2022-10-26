import dayjs from 'dayjs/esm';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { IEventType } from 'app/entities/event-type/event-type.model';

export interface IEventDevice {
  id: string;
  createdAt?: dayjs.Dayjs | null;
  theoreticalPercentage?: boolean | null;
  moneyDenomination?: number | null;
  establishment?: Pick<IEstablishment, 'id'> | null;
  eventType?: Pick<IEventType, 'id'> | null;
}

export type NewEventDevice = Omit<IEventDevice, 'id'> & { id: null };
