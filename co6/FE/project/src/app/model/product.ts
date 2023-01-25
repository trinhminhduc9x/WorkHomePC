import {Category} from './category';
import {User} from './User';

export interface Product {
  id?: number;
  name?: string;
  initialPrice?: string;
  priceStep?: string;
  startDate?: string;
  endDate?: number;
  description?: string;
  imgUrl?: string;
  category?: Category;
  user?: User;
}
