import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from '../../shared/services/http-helper';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  public responseCache = new Map();
  constructor(private http: HttpClient) { }

  getCategoryList() {
    const categoryListFromCache = this.responseCache.get(`${HttpHelper.baseURL}/categories/list`);
    if (categoryListFromCache) {
      return of(categoryListFromCache);
    }
    const response = this.http.get(`${HttpHelper.baseURL}/categories/list`);
    response.subscribe(result => this.responseCache.set(`${HttpHelper.baseURL}/categories/list`, result));
    return response;
  }
}
