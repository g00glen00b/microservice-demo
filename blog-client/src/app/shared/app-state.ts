import {Authentication} from '../authentication/authentication';
import {Alert} from './alert/alert';

export class AppState {
  auth: Authentication;
  appAlert: Alert;
}
