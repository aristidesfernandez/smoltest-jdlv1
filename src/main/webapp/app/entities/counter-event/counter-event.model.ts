import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { IEventDevice } from 'app/entities/event-device/event-device.model';

export interface ICounterEvent {
  id: number;
  valueCounter?: number | null;
  denominationSale?: number | null;
  counterCode?: Pick<ICounterType, 'id'> | null;
  idEventDevice?: Pick<IEventDevice, 'id'> | null;
}

export type NewCounterEvent = Omit<ICounterEvent, 'id'> & { id: null };
