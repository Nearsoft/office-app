import { useDispatch, useSelector } from 'react-redux';

import './App.css';
import { Dispatch, RootState } from './state/store';

function App() {
  const auth = useSelector((state: RootState) => state.auth);
  const dispatch = useDispatch<Dispatch>();

  const loginRequest = () => dispatch.auth.loginRequest('username', 'password');

  return (
    <>
      {auth.isAuthenticated}
      <button onClick={loginRequest}>mockCall</button>
    </>
  );
}

export default App;
