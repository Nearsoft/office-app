import { createModel } from '@rematch/core';

import type { RootModel } from '.';

export interface AuthState {
  token: string | null;
  isAuthenticated: boolean;
}

const initialState: AuthState = { token: null, isAuthenticated: false };
// const api = ApiService.getInstance(); TODO: make sure that implemented singleton

export const auth = createModel<RootModel>()({
  state: initialState,
  reducers: {
    SET_AUTHENTICATION: (state, token: string | null = null) => {
      return { ...state, token, isAuthenticated: true };
    },
  },
});
