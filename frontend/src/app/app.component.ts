import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loadMessages, locale} from 'devextreme/localization';
import config from 'devextreme/core/config';
import {AuthService} from './shared/services';
// @ts-ignore
import plMessages from './../assets/locale/pl.json';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(private authService: AuthService, private http: HttpClient) {
    loadMessages(plMessages);
    locale('pl');
    config({ defaultCurrency: 'PLN', floatingActionButtonConfig: {
          position: { at: 'right bottom', my: 'right bottom', offset: '-80 -16' }
      }
    });
  }

  isAutorized() {
    return this.authService.loggedIn;
  }
}
