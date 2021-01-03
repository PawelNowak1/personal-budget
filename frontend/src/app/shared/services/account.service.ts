import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHelper} from './http-helper';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  constructor(private http: HttpClient) { }
  public accountTypes = [
    {
      id: 1,
      typeName: 'Gotówka'
    },
    {
      id: 2,
      typeName: 'Konto bankowe'
    },
    {
      id: 3,
      typeName: 'Lokata'
    },
    {
      id: 4,
      typeName: 'Karta kredytowa'
    }
  ];

  createAccount(accountData: any) {
    return this.http.post(`${HttpHelper.baseURL}/account/create`, accountData);
  }

  getAccountList(onlyActive: boolean) {
    const params = new HttpParams().set('onlyActive', String(onlyActive));
    return this.http.get(`${HttpHelper.baseURL}/account/list`, {params});
  }

  getAccountSum() {
    return this.http.get(`${HttpHelper.baseURL}/account/sum`);
  }
}
