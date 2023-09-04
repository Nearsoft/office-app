import React from 'react';

import { render, screen } from 'test-utils';
import userEvent from '@testing-library/user-event';
import '@testing-library/jest-dom';

import App from '../App';

test('render correctly', async () => {
  // ARRANGE
  render(<App />);

  // ACT
  await userEvent.click(screen.getByText('Load mockToken'));

  // ASSERT
  expect(screen.getByText('true')).toBeDefined();
});
