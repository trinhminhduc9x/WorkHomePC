
import {Account} from '../account/account';

export interface User {

  idUser?: number;

  name?: string;

  dateOfBirth?: string;

  gender?: boolean;

  address?: string;

  job?: string;

  married?: boolean;

  avatar?: string;

  joinDay?: string;

  coin?: number;
  account?: Account;
}
