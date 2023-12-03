import { useSelector } from 'react-redux';

import './App.css';
import { LoginComponent } from './components/Login';
import { RootState } from './state/store';

const App = () => {
  const auth = useSelector((state: RootState) => state.auth);

  return (
    <>
      {auth.isAuthenticated}
      <LoginComponent />
    </>
  );
};

export default App;
