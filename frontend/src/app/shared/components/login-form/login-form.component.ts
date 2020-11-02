import { Component, NgModule } from '@angular/core';
import {AuthService} from '../../services';
import {AppInfoService} from '../../services/app-info.service';
import { DxButtonModule } from 'devextreme-angular/ui/button';
import { DxCheckBoxModule } from 'devextreme-angular/ui/check-box';
import { DxTextBoxModule } from 'devextreme-angular/ui/text-box';
import { DxValidatorModule } from 'devextreme-angular/ui/validator';
import { DxValidationGroupModule } from 'devextreme-angular/ui/validation-group';
import { CommonModule } from '@angular/common';
import {Router, RouterModule} from '@angular/router';
import {DxLoadIndicatorModule, DxLoadPanelModule, DxScrollViewModule} from 'devextreme-angular';
import {formatMessage} from 'devextreme/localization';
import {first} from 'rxjs/operators';
import notify from 'devextreme/ui/notify';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  login = '';
  password = '';
  createAccountPath = '/createAccount';
  isLoadPanelVisible = false;
  formatMessage = formatMessage;

  constructor(private authService: AuthService, public appInfo: AppInfoService, private router: Router) { }

  onLoginClick(args) {
    if (!args.validationGroup.validate().isValid) {
      return;
    }
    this.isLoadPanelVisible = true;
    this.authService.logIn(this.login, this.password)
      .pipe(first())
      .subscribe(
        data => {
          this.isLoadPanelVisible = false;
          args.validationGroup.reset();
          this.router.navigate(['/home']);
        },
        error => {
          args.validationGroup.reset();
          this.isLoadPanelVisible = false;
          notify({
            message: error.message,
            type: 'error',
            displayTime: 3000,
            position: 'top'
          });
        });
  }

  onCreateAccountClick() {
    this.router.navigate([this.createAccountPath]);
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
    DxScrollViewModule,
    DxLoadPanelModule,
    DxLoadIndicatorModule
  ],
  declarations: [ LoginFormComponent ],
  exports: [ LoginFormComponent ]
})
export class LoginFormModule { }
