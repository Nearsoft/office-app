import { useDispatch, useSelector } from 'react-redux';

import './App.css';
import { Dispatch, RootState } from './state/store';

const App = () => {
  const auth = useSelector((state: RootState) => state.auth);
  const dispatch = useDispatch<Dispatch>();

  const loginRequest = (): Promise<void> =>
    dispatch.auth.loginRequest({ username: 'username', password: 'password' });

  return (
    <>
      {auth.isAuthenticated}
      <button onClick={loginRequest}>Load mockToken</button>
    </>
  );
};

export default App;
