import {Component, ViewChild} from '@angular/core';
import {OperationService} from '../../shared/services/operation.service';
import {formatMessage} from 'devextreme/localization';
import notify from 'devextreme/ui/notify';
import {AddOperationComponent} from '../../shared/popups/add-operation/add-operation.component';

@Component({
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent {
  @ViewChild(AddOperationComponent, {static: false}) addOperationComponent: AddOperationComponent;

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

  removeOpertion(event) {
    this.operationService.deleteOperation(event.data.id).subscribe(() => {
      notify({
        message: 'Operacja usunięta!',
        type: 'info',
        displayTime: 4000,
        position: 'top'
      });
    });
  }

  addOperation() {
    this.addOperationComponent.visible = true;
  }

  onValueChanged(event) {
    this.refreshGridData();
  }
}
