import {Component, ViewChild} from '@angular/core';
import {HomeService} from './home.service';
import {formatMessage} from 'devextreme/localization';
import {AddOperationComponent} from '../../shared/popups/add-operation/add-operation.component';
import {DxDataGridComponent} from 'devextreme-angular';
import {OperationService} from '../../shared/services/operation.service';

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  @ViewChild(AddOperationComponent, {static: false}) addOperationComponent: AddOperationComponent;
  @ViewChild(DxDataGridComponent, {static: false}) dataGrid: DxDataGridComponent;

  monthYear: Date = new Date();
  formatMessage = formatMessage;
  operations: any;

  constructor(private homeService: HomeService,
              private operationService: OperationService) {
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
      if (cellInfo?.data?.items?.[0]?.categoryType === 'expense' || cellInfo?.data?.collapsedItems?.[0]?.categoryType === 'expense') {
        cellInfo.cellElement.style.color = 'red';
      } else {
        cellInfo.cellElement.style.color = 'lightgreen';
      }
    }
    return cellInfo.data.key.split(';')[1];
  }

  onToolbarPreparing(event) {
    const itemExpand = {
      location: 'before',
      widget: 'dxButton',
      options: {
        text: 'Rozwiń wszystko',
        icon: 'expand',
        onClick: (e) => {
          this.dataGrid.instance.expandAll();
        }
      }
    };

    const itemCollapse = {
      location: 'before',
      widget: 'dxButton',
      options: {
        text: 'Zwiń wszystko',
        icon: 'collapse',
        onClick: (e) => {
          this.dataGrid.instance.collapseAll();
        }
      }
    };

    const itemDateBox = {
      location: 'after',
      template: 'dateBox'
    };

    event.toolbarOptions.items.push(itemExpand);
    event.toolbarOptions.items.push(itemCollapse);
    event.toolbarOptions.items.unshift(itemDateBox);
  }

  onRowUpdating(event) {
    const budget = {
      plan: event.newData.plan,
      budgetPlanId: event.oldData.budgetPlanId,
      monthYear: this.monthYear,
      categoryId: event.oldData.categoryId
    };
    this.operationService.createBudgetPlan(budget).subscribe(() => {
      this.refreshGridData();
    });
  }
}
