import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from '../../shared/services/http-helper';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient) { }

  getCategoryList() {
    return this.http.get(`${HttpHelper.baseURL}/categories/list`);
  }

  getOnlyParentCategories() {
    return this.http.get(`${HttpHelper.baseURL}/categories/onlyParent`);
  }

  createCategory(categoryData) {
    return this.http.post(`${HttpHelper.baseURL}/categories/create`, categoryData);
  }
}
