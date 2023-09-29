import { renderWithProviders } from '../../../../utils/test-utils';
import { store } from '../../../state/store';
import { LoginComponent } from '../index';

test('render correctly', () => {
  renderWithProviders(<LoginComponent />, store);
});
