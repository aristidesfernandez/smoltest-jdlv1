export interface IIsland {
  id: number;
  description?: string | null;
  name?: string | null;
}

export type NewIsland = Omit<IIsland, 'id'> & { id: null };
