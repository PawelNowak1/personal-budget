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
    this.refreshGridData();
  }

  refreshGridData() {
    this.homeService.getMonthlyView(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
  }

  onValueChanged(event) {
    this.refreshGridData();
  }

  calcGroupValue(rowData) {
    return rowData.parentOrdNumber + ';' + rowData.category;
  }

  addOperation() {
    this.addOperationComponent.visible = true;
  }

  getTitle(cellInfo) {
    if (cellInfo.cellElement) {
      if (cellInfo?.data?.items[0]?.categoryType === 'expense') {
        cellInfo.cellElement.style.color = 'red';
      } else {
        cellInfo.cellElement.style.color = 'lightgreen';
      }
    }
    return cellInfo.data.key.split(';')[1];
  }

}
