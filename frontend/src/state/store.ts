import { RematchDispatch, RematchRootState, init } from '@rematch/core';
import immerPlugin from '@rematch/immer';
import loadingPlugin, { ExtraModelsFromLoading } from '@rematch/loading';

import { RootModel, models } from './models';

type FullModel = ExtraModelsFromLoading<RootModel, { type: 'full' }>;

export const store = init<RootModel, FullModel>({
  models,
  // add plugins to store
  plugins: [immerPlugin(), loadingPlugin({ type: 'full' })],
});

export type Store = typeof store;
export type Dispatch = RematchDispatch<RootModel>;
export type RootState = RematchRootState<RootModel, FullModel>;
