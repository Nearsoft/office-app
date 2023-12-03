import { useState } from 'react';
import { useDispatch } from 'react-redux';

import { useMutation } from '@tanstack/react-query';

import { LoginMutation } from '@/state/service/api.service';

import LoginForm from './LoginForm';
import './login.scss';

export const LoginComponent = () => {
  const [mode, setMode] = useState('login');
  const dispatch = useDispatch();
  const loginMutation = useMutation({
    mutationFn: async (credentials: { email: string; password: string }) => {
      LoginMutation(credentials);
    },
  });

  const toggleMode = () => {
    if (mode === 'login') {
      return setMode('signup');
    }
    return setMode('login');
  };

  const onSubmit = (credentials) => {
    loginMutation.mutate(credentials);
  };
  if (loginMutation.isLoading) {
    return 'Loading..';
  }
  if (loginMutation.isSuccess) {
    // TODO: check this property in order to retrieve correctly to use on headers as correct auth
    const { token } = loginMutation.data;
    dispatch.auth.SET_AUTHENTICATION(token);
  }
  return (
    <div className={`app app--is-${mode}`}>
      <div className={`form-block-wrapper form-block-wrapper--is-${mode}`}></div>
      <section className={`form-block form-block--is-${mode}`}>
        <header className="form-block__header">
          <h1>{mode === 'login' ? 'Welcome back!' : 'Sign up'}</h1>
          <div className="form-block__toggle-block">
            <span>
              {mode === 'login' ? "Don't" : 'Already'} have an account? Click here &#8594;
            </span>
            <input id="form-toggler" type="checkbox" onChange={toggleMode} />
            <label htmlFor="form-toggler"></label>
          </div>
        </header>
        <LoginForm mode={mode} onSubmit={onSubmit} />
      </section>
    </div>
  );
};

export default LoginComponent;
