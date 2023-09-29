import { renderWithProviders } from '../../utils/test-utils';
import App from '../App';
import { store } from '../state/store';

test('render correctly', () => {
  renderWithProviders(<App />, store);
});
