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
});
