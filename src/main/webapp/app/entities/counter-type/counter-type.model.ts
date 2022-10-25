export interface ICounterType {
  id: number;
  counterCode?: string | null;
  name?: string | null;
  description?: string | null;
  includedInFormula?: boolean | null;
  prize?: boolean | null;
  category?: string | null;
  udteWaitTime?: number | null;
}

export type NewCounterType = Omit<ICounterType, 'id'> & { id: null };
