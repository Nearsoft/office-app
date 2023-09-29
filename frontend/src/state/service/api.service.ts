const API_BASE_URL = 'http://localhost:8080/api';

export const LoginMutation = async (credentials: { email: string; password: string }) => {
  const res = await fetch(`${API_BASE_URL}/signup`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(credentials),
  });
  return res.json();
};
