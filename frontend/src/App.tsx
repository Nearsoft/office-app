import { useSelector } from 'react-redux';

import { useMutation } from '@tanstack/react-query';

import './App.css';
import { LoginMutation } from './state/service/api.service';
import { RootState } from './state/store';

const App = () => {
  const auth = useSelector((state: RootState) => state.auth);

  const mutation = useMutation({
    mutationFn: LoginMutation,
  });

  const loginRequest = (): void =>
    mutation.mutate({ email: 'email@domain.com', password: 'password' });

  if (mutation.isLoading) {
    return 'Loading..';
  }

  return (
    <>
      {auth.isAuthenticated}
      <button onClick={() => loginRequest()}>Load mockToken</button>
    </>
  );
};

export default App;
