import { init } from '@rematch/core';

import { RootModel, models } from '..';

describe('[auth] model', () => {
  it('reducer: SET_AUTHENTICATION', () => {
    const token = 'token';
    const store = init<RootModel>({
      models,
    });
    store.dispatch.auth.SET_AUTHENTICATION(token);
    const myModelData = store.getState();
    expect(myModelData.auth.token).toEqual(token);
    expect(myModelData.auth.isAuthenticated).toBe(true);
  });

  it('effect: loginRequest', async () => {
    const userCrendentials = {
      username: "username",
      password: "password"
    };
    const store = init<RootModel>({
      models,
    });
    await store.dispatch.auth.loginRequest(userCrendentials);
    const state = store.getState();
    expect(state.auth.token).toEqual(userCrendentials.username);
    expect(state.auth.isAuthenticated).toBe(true);
  });
});
