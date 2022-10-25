import dayjs from 'dayjs/esm';

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
  municipalityCode?: string | null;
  brand?: string | null;
}

export type NewOperator = Omit<IOperator, 'id'> & { id: null };
