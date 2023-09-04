import type { Config } from '@jest/types';

const config: Config.InitialOptions = {
  verbose: true,
  transform: {
    '^.+\\.tsx?$': 'ts-jest',
  },
  testEnvironment: 'jsdom',
  moduleDirectories: [
    'node_modules',
    'utils', // a utility folder
    __dirname, // the root directory
  ],
};

export default config;
