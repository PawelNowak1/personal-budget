import {Component, ViewChild} from '@angular/core';
import {AccountService} from '../../shared/services/account.service';
import {formatMessage} from 'devextreme/localization';
import {AddAccountComponent} from '../../shared/popups/add-account/add-account.component';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent {
  @ViewChild(AddAccountComponent, {static: false}) addAccountComponent: AddAccountComponent;
  accounts: any;
  formatMessage = formatMessage;
  accountTypes: any;

  constructor(private accountService: AccountService) {
    this.accountService.getAccountList(false).subscribe((result) => {
      this.accounts = result;
    });
    this.accountTypes = this.accountService.accountTypes;
  }

  updateAccount(event) {
    this.accountService.createAccount(event.data).subscribe(() => {
      this.refreshGridData();
    });
  }

  addAccount() {
    this.addAccountComponent.visible = true;
  }

  refreshGridData() {
    this.accountService.getAccountList(false).subscribe((result) => {
      this.accounts = result;
    });
  }
}
