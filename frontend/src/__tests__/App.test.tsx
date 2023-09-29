import { renderWithRematchStore } from '../../utils/test-utils';
import App from '../App';
import { store } from '../state/store';

test('render correctly', () => {
  renderWithRematchStore(<App />, store);
});
