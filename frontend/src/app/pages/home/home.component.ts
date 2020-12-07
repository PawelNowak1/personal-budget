import {Component, ViewChild} from '@angular/core';
import {HomeService} from './home.service';
import {formatMessage} from 'devextreme/localization';
import {AddOperationComponent} from '../../shared/popups/add-operation/add-operation.component';
import {DxDataGridComponent} from 'devextreme-angular';
import {OperationService} from '../../shared/services/operation.service';
import {AccountService} from "../../shared/services/account.service";

@Component({
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  @ViewChild(AddOperationComponent, {static: false}) addOperationComponent: AddOperationComponent;
  @ViewChild(DxDataGridComponent, {static: false}) dataGrid: DxDataGridComponent;

  monthYear: Date = new Date();
  formatMessage = formatMessage;
  accountSum = 0;
  operations: any;

  constructor(private homeService: HomeService,
              private operationService: OperationService,
              private accountService: AccountService) {
    this.refreshGridData();
    this.calculateGridSummaries = this.calculateGridSummaries.bind(this);

  }

  refreshGridData() {
    this.homeService.getMonthlyView(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
    this.refreshAccountSumAmount();
  }

  refreshAccountSumAmount() {
    this.accountService.getAccountSum().subscribe((result: number) => {
      this.accountSum = result;
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

  calculateGridSummaries(options) {
    if (options.name === 'summaryRealIncome') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'income') {
          options.totalValue = options.totalValue + options.value.real;
        }
      }
    }
    if (options.name === 'summaryRealExpense') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'expense') {
          options.totalValue = options.totalValue + options.value.real;
        }
      }
    }

    if (options.name === 'summaryPlanIncome') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'income') {
          options.totalValue = options.totalValue + options.value.plan;
        }
      }
    }
    if (options.name === 'summaryPlanExpense') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'expense') {
          options.totalValue = options.totalValue + options.value.plan;
        }
      }
    }

    if (options.name === 'summaryDiffIncome') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'income') {
          options.totalValue = options.totalValue + options.value.difference;
        }
      }
    }
    if (options.name === 'summaryDiffExpense') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value.categoryType === 'expense') {
          options.totalValue = options.totalValue + options.value.difference;
        }
      }
    }

    if (options.name === 'summaryAccountSum') {
      if (options.summaryProcess === 'finalize') {
        options.totalValue = this.accountSum;
      }
    }
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

    const itemAddOperation = {
      location: 'after',
      widget: 'dxButton',
      options: {
        icon: 'add',
        onClick: (e) => {
          this.addOperation();
        }
      }
    };

    event.toolbarOptions.items.push(itemExpand);
    event.toolbarOptions.items.push(itemCollapse);
    event.toolbarOptions.items.unshift(itemDateBox);
    event.toolbarOptions.items.unshift(itemAddOperation);
  }

  onCellPrepared(e) {
    if (e.rowType === 'data' && e.column?.dataField === 'difference') {
      e.cellElement.style.color = e.data.difference >= 0 ? 'lightgreen' : 'red';
      e.cellElement.style.fontWeight = 'bold';
    }
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
