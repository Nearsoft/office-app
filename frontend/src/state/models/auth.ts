import { createModel } from '@rematch/core';

import type { RootModel } from '.';
import { delay } from './utils';

// import { ApiService } from '../service/api.service';

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
  effects: (dispatch) => ({
    async loginRequest(payload: { username: string; password: string }) {
      const { username, password } = payload;
      console.log(password);
      await delay(500);
      dispatch.auth.SET_AUTHENTICATION(username); // TODO: this is only a mock value
    },
  }),
});
