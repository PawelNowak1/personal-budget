import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHelper} from '../../shared/services/http-helper';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  getMonthlyView(month: number, year: number) {
    const params = new HttpParams().set('month', (month + 1).toString()).set('year', year.toString());
    return this.http.get(`${HttpHelper.baseURL}/budget/monthly-view`, {params});
  }
}
