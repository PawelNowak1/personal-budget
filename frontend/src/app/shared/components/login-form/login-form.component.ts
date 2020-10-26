import { Component, NgModule } from '@angular/core';
import {AuthService} from '../../services';
import {AppInfoService} from '../../services/app-info.service';
import { DxButtonModule } from 'devextreme-angular/ui/button';
import { DxCheckBoxModule } from 'devextreme-angular/ui/check-box';
import { DxTextBoxModule } from 'devextreme-angular/ui/text-box';
import { DxValidatorModule } from 'devextreme-angular/ui/validator';
import { DxValidationGroupModule } from 'devextreme-angular/ui/validation-group';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {DxScrollViewModule} from 'devextreme-angular';
import {formatMessage} from 'devextreme/localization';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  login = '';
  password = '';
  formatMessage = formatMessage;

  constructor(private authService: AuthService, public appInfo: AppInfoService) { }

  onLoginClick(args) {
    if (!args.validationGroup.validate().isValid) {
      return;
    }

    this.authService.logIn(this.login, this.password);

    args.validationGroup.reset();
  }
}
@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    DxButtonModule,
    DxCheckBoxModule,
    DxTextBoxModule,
    DxValidatorModule,
    DxValidationGroupModule,
    DxScrollViewModule
  ],
  declarations: [ LoginFormComponent ],
  exports: [ LoginFormComponent ]
})
export class LoginFormModule { }
