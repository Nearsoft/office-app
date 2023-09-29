import React from 'react';
import { Provider } from 'react-redux';

import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { render } from '@testing-library/react';
import type { Store } from 'redux';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      // ? turns retries off
      retry: false,
    },
  },
});

export const renderWithProviders = (ui: React.ReactElement, store: Store) =>
  render(ui, {
    wrapper: ({ children }) => (
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
      </Provider>
    ),
  });
