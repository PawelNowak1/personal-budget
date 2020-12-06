import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {formatMessage} from 'devextreme/localization';
import {CategoriesService} from '../../../pages/categories/categories.service';
import DataSource from 'devextreme/data/data_source';
import ArrayStore from 'devextreme/data/array_store';
import {AccountService} from '../../services/account.service';
import {AddAccountComponent} from '../add-account/add-account.component';
import {DxFormComponent} from 'devextreme-angular';
import {OperationService} from '../../services/operation.service';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-add-operation',
  templateUrl: './add-operation.component.html',
  styleUrls: ['./add-operation.component.css']
})
export class AddOperationComponent implements OnInit {
  @Input() visible = false;
  @Output() refreshGrid = new EventEmitter();
  @ViewChild(AddAccountComponent, {static: false}) addAccountComponent: AddAccountComponent;
  @ViewChild(DxFormComponent, {static: false}) formComponent: DxFormComponent;

  formatMessage = formatMessage;
  operationData: any = {operationDate: new Date(), amount: null, accountId: null};
  fromUngroupedData: DataSource;
  accountList: any = {};

  constructor(private categoriesService: CategoriesService,
              private accountService: AccountService,
              private operationService: OperationService) {
  }

  ngOnInit(): void {
    this.categoriesService.getCategoryList().subscribe(
      (result: any) => this.fromUngroupedData = new DataSource({
        store: new ArrayStore({
          data: result,
          key: 'id'
        }),
        group: 'category'
      }));

    this.accountService.getAccountList().subscribe(result => this.accountList = result);
  }

  addOperation(addNextOperation: boolean) {
    if (!this.formComponent || !this.formComponent.instance.validate().isValid || !this.operationData.accountId) {
       return;
    }
    this.operationService.createOperation(this.operationData).subscribe((result) => {
      notify({
        message: 'Operacja dodana!',
        type: 'success',
        displayTime: 4000,
        position: 'top'
      });
      this.operationData.amount = null;
      this.operationData.description = null;
      this.refreshGrid.emit();
      if (!addNextOperation) {
        this.visible = false;
      }
    });
  }

  nextOperationFalse = () => {
    this.addOperation(false);
  }

  nextOperationTrue = () => {
    this.addOperation(true);
  }

}
