import { Component } from '@angular/core';
import {OperationService} from '../../shared/services/operation.service';
import {formatMessage} from 'devextreme/localization';

@Component({
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent {
  monthYear: Date = new Date();
  operations: any;

  formatMessage = formatMessage;

  constructor(private operationService: OperationService) {
    this.refreshGridData();
  }

  refreshGridData() {
    this.operationService.getOperations(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
  }

  onValueChanged(event) {
    this.refreshGridData();
  }
}
