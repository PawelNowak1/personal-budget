import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {HttpHelper} from '../../shared/services/http-helper';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  // example data
  operations = [
    {
      id: 1,
      category: 'Jedzenie',
      subcategory: 'W domu',
      plan: 2300,
      real: 2400,
      difference: -100
    },
    {
      id: 2,
      category: 'Jedzenie',
      subcategory: 'W pracy',
      plan: 2900,
      real: 2400,
      difference: 500
    },
    {
      id: 3,
      category: 'Jedzenie',
      subcategory: 'Na mieście',
      plan: 2300,
      real: 2300,
      difference: 0
    },
    {
      id: 4,
      category: 'Transport',
      subcategory: 'Benzyna',
      plan: 54,
      real: 12,
      difference: 0
    },
    {
      id: 5,
      category: 'Transport',
      subcategory: 'Bilety',
      plan: 12,
      real: 2,
      difference: 0
    },
    {
      id: 6,
      category: 'Przychody',
      subcategory: 'Wynagrodzenie',
      plan: 230.23,
      real: 230,
      difference: 0
    },
    {
      id: 7,
      category: 'Przychody',
      subcategory: 'Premia',
      plan: 2300,
      real: 2100,
      difference: 0
    },
  ];
  constructor(private http: HttpClient) { }

  getMonthlyView(month: number, year: number) {
    const params = new HttpParams().set('month', (month + 1).toString()).set('year', year.toString());
    return this.http.get(`${HttpHelper.baseURL}/budget/monthly-view`, {params});
  }
}
