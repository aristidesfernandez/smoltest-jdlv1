import { IModel } from 'app/entities/model/model.model';

export interface IEventType {
  id: number;
  eventCode?: string | null;
  sasCode?: string | null;
  description?: string | null;
  isStorable?: boolean | null;
  isPriority?: boolean | null;
  procesador?: string | null;
  isAlarm?: boolean | null;
  idEventTypes?: Pick<IModel, 'id'>[] | null;
}

export type NewEventType = Omit<IEventType, 'id'> & { id: null };
