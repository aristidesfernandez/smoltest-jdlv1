import dayjs from 'dayjs/esm';
import { IMunicipality } from 'app/entities/municipality/municipality.model';

export interface IOperator {
  id: number;
  permitDescription?: string | null;
  endDate?: dayjs.Dayjs | null;
  startDate?: dayjs.Dayjs | null;
  minAccumulatedPrize?: number | null;
  minIndividualPrize?: number | null;
  minTransactionAccumulated?: number | null;
  minIndividualTransaction?: number | null;
  nit?: string | null;
  contractNumber?: string | null;
  eventQuantity?: number | null;
  companyName?: string | null;
  brand?: string | null;
  municipality?: Pick<IMunicipality, 'id'> | null;
}

export type NewOperator = Omit<IOperator, 'id'> & { id: null };
