import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from './http-helper';
import {of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  public responseCache = new Map();
  constructor(private http: HttpClient) { }

  createAccount(accountData: any) {
    return this.http.post(`${HttpHelper.baseURL}/account/create`, accountData);
  }

  getAccountList() {
    const accountListFromCache = this.responseCache.get(`${HttpHelper.baseURL}/account/list`);
    if (accountListFromCache) {
      return of(accountListFromCache);
    }
    const response = this.http.get(`${HttpHelper.baseURL}/account/list`);
    response.subscribe(result => this.responseCache.set(`${HttpHelper.baseURL}/account/list`, result));
    return response;
  }
}
