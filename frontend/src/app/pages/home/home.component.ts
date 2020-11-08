import {Component, ViewChild} from '@angular/core';
import {HomeService} from './home.service';
import {formatMessage} from 'devextreme/localization';
import {AddAccountComponent} from '../../shared/popups/add-account/add-account.component';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  @ViewChild(AddAccountComponent, {static: false}) addAccountComponent: AddAccountComponent;

  monthYear: Date = new Date();
  formatMessage = formatMessage;
  operations: any;

  constructor(private homeService: HomeService) {
    this.operations = this.homeService.operations;
  }

  onValueChanged(event) {
    this.addAccountComponent.visible = true;
  }
}
