import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHelper} from './http-helper';

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  constructor(private http: HttpClient) { }

  createOperation(operationData: any) {
    return this.http.post(`${HttpHelper.baseURL}/operation/create`, operationData);
  }

  getOperations(month?: number, year?: number) {
    if (month && year) {
      const params = new HttpParams().set('month', (month + 1).toString()).set('year', year.toString());
      return this.http.get(`${HttpHelper.baseURL}/operation/get`, {params});
    } else {
      return this.http.get(`${HttpHelper.baseURL}/operation/get`);
    }
  }

  deleteOperation(operationId: number) {
    return this.http.delete(`${HttpHelper.baseURL}/operation/delete/${operationId}`);
  }

  createBudgetPlan(budget: any) {
    return this.http.post(`${HttpHelper.baseURL}/budget/create`, budget);
  }
}
