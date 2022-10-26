import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { IEventDevice } from 'app/entities/event-device/event-device.model';

export interface ICounterEvent {
  id: string;
  valueCounter?: number | null;
  denominationSale?: number | null;
  counterCode?: Pick<ICounterType, 'counterCode'> | null;
  idEventDevice?: Pick<IEventDevice, 'id'> | null;
}

export type NewCounterEvent = Omit<ICounterEvent, 'id'> & { id: null };
