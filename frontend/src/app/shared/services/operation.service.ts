import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from './http-helper';

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  constructor(private http: HttpClient) { }

  createOperation(operationData: any) {
    return this.http.post(`${HttpHelper.baseURL}/operation/create`, operationData);
  }
}
