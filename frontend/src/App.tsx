import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import './App.css';
import { Dispatch, RootState } from './state/store';

const App = () => {
  const auth = useSelector((state: RootState) => state.auth);
  const dispatch = useDispatch<Dispatch>();

  const loginRequest = () =>
    dispatch.auth.loginRequest({ username: 'username', password: 'password' });

  return (
    <div>
      {auth.isAuthenticated}
      <button onClick={loginRequest}>Load mockToken</button>
    </div>
  );
}

export default App;
