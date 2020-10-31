import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpHelper} from '../../shared/services/http-helper';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }
}
