import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loadMessages, locale} from 'devextreme/localization';
import config from 'devextreme/core/config';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(private http: HttpClient) {
    this.http.get('assets/locale/pl.json').subscribe(result => this.initMessages(result));
  }

  private initMessages(result): void {
    result = JSON.parse('{"pl":' + JSON.stringify(result) + '}');
    loadMessages(result);
    locale('pl');
    config({ defaultCurrency: 'PLN' });
  }
}
