import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { IDevice } from 'app/entities/device/device.model';

export interface ICounterDevice {
  id: number;
  value?: number | null;
  rolloverValue?: number | null;
  creditSale?: number | null;
  manualCounter?: boolean | null;
  manualMultiplier?: number | null;
  decimalsManualCounter?: boolean | null;
  counterCode?: Pick<ICounterType, 'id'> | null;
  idDevice?: Pick<IDevice, 'id'> | null;
}

export type NewCounterDevice = Omit<ICounterDevice, 'id'> & { id: null };
