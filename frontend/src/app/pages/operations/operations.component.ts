import {Component, ViewChild} from '@angular/core';
import {OperationService} from '../../shared/services/operation.service';
import {formatMessage} from 'devextreme/localization';
import notify from 'devextreme/ui/notify';
import {AddOperationComponent} from '../../shared/popups/add-operation/add-operation.component';
import {AccountService} from '../../shared/services/account.service';
import {CategoriesService} from '../categories/categories.service';

@Component({
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent {
  @ViewChild(AddOperationComponent, {static: false}) addOperationComponent: AddOperationComponent;

  monthYear: Date = new Date();
  operations: any;
  accountSum = 0;
  subcategoryList: any;
  parentCategoryList: any;
  accountList: any;

  formatMessage = formatMessage;

  constructor(private operationService: OperationService,
              private accountService: AccountService,
              private categoryService: CategoriesService) {
    this.getFilteredSubcategories = this.getFilteredSubcategories.bind(this);
    this.refreshGridData();
    this.calculateGridSummaries = this.calculateGridSummaries.bind(this);
    this.categoryService.getOnlyParentCategories().subscribe((result) => this.parentCategoryList = result);
    this.categoryService.getCategoryList().subscribe((result) => this.subcategoryList = result);
    this.accountService.getAccountList(true).subscribe((result) => this.accountList = result);
  }

  refreshGridData() {
    this.operationService.getOperations(this.monthYear.getMonth(), this.monthYear.getFullYear()).subscribe((operation) => {
      this.operations = operation;
    });
    this.refreshAccountSumAmount();
    this.accountService.getAccountList(true).subscribe((result) => this.accountList = result);
  }

  onToolbarPreparing(event) {
    const itemDateBox = {
      location: 'after',
      template: 'dateBox'
    };

    event.toolbarOptions.items.push(itemDateBox);
  }

  onCellPrepared(e) {
    if (e.rowType === 'data' && e.column?.dataField === 'amount') {
      e.cellElement.style.color = e.data?.category?.parent?.type === 'income' ? 'lightgreen' : 'red';
      e.cellElement.style.fontWeight = 'bold';
    }
  }

  removeOpertion(event) {
    this.operationService.deleteOperation(event.data.id).subscribe(() => {
      notify({
        message: 'Operacja usunięta!',
        type: 'info',
        displayTime: 4000,
        position: 'top'
      });
      this.refreshGridData();
    });
  }

  onRowUpdated(event) {
    this.operationService.updateOperation({
      accountId: event.data.account.id,
      operationDate: event.data.operationDate,
      description: event.data.description,
      categoryId: event.data.category.id,
      amount: event.data.amount,
      operationId: event.data.id
    }).subscribe(() => {
      this.refreshGridData();
      notify({
        message: 'Operacja zmodyfikowana!',
        type: 'info',
        displayTime: 4000,
        position: 'top'
      });
    });
  }

  calculateGridSummaries(options) {
    if (options.name === 'summaryRealIncome') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value?.category?.parent?.type === 'income') {
          options.totalValue = options.totalValue + options.value.amount;
        }
      }
    }
    if (options.name === 'summaryRealExpense') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value?.category?.parent?.type === 'expense') {
          options.totalValue = options.totalValue + options.value.amount;
        }
      }
    }
    if (options.name === 'summaryRealDiff') {
      if (options.summaryProcess === 'start') {
        options.totalValue = 0;
      } else if (options.summaryProcess === 'calculate') {
        if (options.value?.category?.parent?.type === 'expense') {
          options.totalValue = options.totalValue - options.value.amount;
        } else {
          options.totalValue = options.totalValue + options.value.amount;
        }
      }
    }

    if (options.name === 'summaryAccountSum') {
      if (options.summaryProcess === 'finalize') {
        options.totalValue = this.accountSum;
      }
    }
  }

  refreshAccountSumAmount() {
    this.accountService.getAccountSum().subscribe((result: number) => {
      this.accountSum = result;
    });
  }

  setCategoryValue(newData, value, currentRowData) {
    newData.category = currentRowData.category;
    newData.category.id = null;
    (this as any).defaultSetCellValue(newData, value);
  }

  getFilteredSubcategories(options) {
    console.log(options);
    return {
      store: this.subcategoryList,
      filter: options.data ? ['parentCategoryId', '=', options.data.category.parent.id] : null
    };
  }


  addOperation() {
    this.addOperationComponent.visible = true;
  }

  onValueChanged(event) {
    this.refreshGridData();
  }
}
