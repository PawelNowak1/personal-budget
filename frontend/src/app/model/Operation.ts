import {Category} from './Category';

export interface Operation {
  id: number;
  name: string;
  createDate: Date;
  description: string;
  category: Category;
}
