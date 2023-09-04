import { init } from '@rematch/core';

import { RootModel, models } from '..';

describe('[auth] model', () => {
  it('reducer: SET_AUTHENTICATION', async () => {
    const token = 'token';
    const store = init<RootModel>({
      models,
    });
    await store.dispatch.auth.SET_AUTHENTICATION(token);
    const myModelData = store.getState();
    expect(myModelData.auth.token).toEqual(token);
    expect(myModelData.auth.isAuthenticated).toBe(true);
  });

  it('effect: loginRequest', async () => {
    // TODO: this test is actually to verify the mock is called correctly
    const username = 'username';
    const password = 'password';
    const store = init<RootModel>({
      models,
    });
    await store.dispatch.auth.loginRequest({ username, password });
    const state = store.getState();
    expect(state.auth.token).toEqual(username);
    expect(state.auth.isAuthenticated).toBe(true);
  });
});
