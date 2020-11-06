import { Component } from '@angular/core';
import {HomeService} from './home.service';
import {formatMessage} from 'devextreme/localization';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  monthYear: Date = new Date();
  formatMessage = formatMessage;
  operations: any;

  constructor(private homeService: HomeService) {
    this.operations = this.homeService.operations;
  }
}
