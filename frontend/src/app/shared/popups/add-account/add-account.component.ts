import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
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
  @Output() refreshGrid = new EventEmitter();
  accountData: any = {active: true};

  accountTypes: any;

  currency = ['PLN', 'EUR', 'USD'];
  active = true;
  formatMessage = formatMessage;
  constructor(private accountService: AccountService) {
    this.accountTypes = this.accountService.accountTypes;
  }

  submit = () => {
    if (!this.validationGroup.instance.validate().isValid) {
      return;
    }
    this.accountService.createAccount(this.accountData).subscribe((result) => {
      this.refreshGrid.emit();
      this.visible = false;
    });
  }
}
