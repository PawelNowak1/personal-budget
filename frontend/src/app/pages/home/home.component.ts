import {Component, ViewChild} from '@angular/core';
import {HomeService} from './home.service';
import {formatMessage} from 'devextreme/localization';
import {AddOperationComponent} from '../../shared/popups/add-operation/add-operation.component';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  @ViewChild(AddOperationComponent, {static: false}) addOperationComponent: AddOperationComponent;

  monthYear: Date = new Date();
  formatMessage = formatMessage;
  operations: any;

  constructor(private homeService: HomeService) {
    this.homeService.getMonthlyView(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
  }

  refreshGridData() {
    this.homeService.getMonthlyView(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
  }

  onValueChanged(event) {
    this.refreshGridData();
  }

  addOperation() {
    this.addOperationComponent.visible = true;
  }
}
