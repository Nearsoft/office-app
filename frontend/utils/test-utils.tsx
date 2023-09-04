import React, { ReactElement } from 'react';
import { Provider } from 'react-redux';

import { RenderOptions, render } from '@testing-library/react';

import { store } from '../src/state/store';

const AllTheProviders = ({ children }: { children: React.ReactNode }) => {
    return <Provider store={store}>{children}</Provider>;
};

const customRender = (
  ui: ReactElement,
  options?: Omit<RenderOptions, 'wrapper'>,
) => render(ui, {wrapper: AllTheProviders, ...options})

export * from '@testing-library/react'
export {customRender as render}
