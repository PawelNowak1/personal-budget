import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {formatMessage} from 'devextreme/localization';
import {DxValidationGroupComponent} from 'devextreme-angular';
import {AccountService} from '../../services/account.service';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent {
  @Input() visible = false;
  @ViewChild(DxValidationGroupComponent, {static: false}) validationGroup: DxValidationGroupComponent;
  accountData: any = {active: true};

  accountTypes = [
    {
      id: 1,
      typeName: 'Gotówka'
    },
    {
      id: 2,
      typeName: 'Konto bankowe'
    },
    {
      id: 3,
      typeName: 'Lokata'
    },
    {
      id: 4,
      typeName: 'Karta kredytowa'
    }
  ];

  currency = ['PLN', 'EUR', 'USD'];
  active = true;
  formatMessage = formatMessage;
  constructor(private accountService: AccountService) { }

  submit = () => {
    if (!this.validationGroup.instance.validate().isValid) {
      return;
    }
    this.accountService.createAccount(this.accountData).subscribe((result) => {
        this.visible = false;
    });
  }
}
