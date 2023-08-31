import { createModel } from '@rematch/core';
import { delay } from './utils';
import type { RootModel } from '.';

export interface AuthState {
  token: string | null;
  isAuthenticated: boolean;
}

const initialState: AuthState = { token: null, isAuthenticated: false };

export const auth = createModel<RootModel>()({
  state: initialState,
  reducers: {
    setAuthentication: (state, token: string | null = null) => {
      return { ...state, token };
    },
  },
  effects: (dispatch) => ({
    async incrementAsync() {
      await delay(500);
      dispatch.auth.setAuthentication();
    },
  }),
});
