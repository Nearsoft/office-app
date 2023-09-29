import { useState } from 'react';

import { Input } from '../ui/input';

// eslint-disable-next-line no-unused-vars
const LoginForm = ({
  mode,
  onSubmit,
}: {
  mode: string;
  onSubmit: (loginValues: unknown) => void;
}) => {
  const [loginValues, setLoginValues] = useState({
    email: '',
    password: '',
    fullname: '',
    repeatPassword: '',
  });
  const { email, password, fullname, repeatPassword } = loginValues;
  const handleOnChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLoginValues({ ...loginValues, [event.target.id]: event.target.value });
  };
  return (
    <form onSubmit={() => onSubmit(loginValues)}>
      <div className="form-block__input-wrapper">
        <div className="form-group form-group--login">
          <Input
            onChange={handleOnChange}
            type="email"
            id="email"
            label="email"
            value={email}
            disabled={mode === 'signup'}
          />
          <Input
            onChange={handleOnChange}
            type="password"
            id="password"
            label="password"
            value={password}
            disabled={mode === 'signup'}
          />
        </div>
        <div className="form-group form-group--signup">
          <Input
            onChange={handleOnChange}
            type="text"
            id="fullname"
            label="full name"
            value={fullname}
            disabled={mode === 'login'}
          />
          <Input
            onChange={handleOnChange}
            type="email"
            id="email"
            value={email}
            label="email"
            disabled={mode === 'login'}
          />
          <Input
            onChange={handleOnChange}
            type="password"
            value={password}
            id="createpassword"
            label="password"
            disabled={mode === 'login'}
          />
          <Input
            onChange={handleOnChange}
            type="password"
            id="repeatpassword"
            label="repeat password"
            value={repeatPassword}
            disabled={mode === 'login'}
          />
        </div>
      </div>
      <button className="button button--primary full-width" type="submit">
        {mode === 'login' ? 'Log In' : 'Sign Up'}
      </button>
    </form>
  );
};

export default LoginForm;
