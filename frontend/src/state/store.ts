import { RematchDispatch, RematchRootState, init } from '@rematch/core';

import { RootModel, models } from './models';

export const store = init({
  models,
});

export type Store = typeof store;
export type Dispatch = RematchDispatch<RootModel>;
export type RootState = RematchRootState<RootModel>;
