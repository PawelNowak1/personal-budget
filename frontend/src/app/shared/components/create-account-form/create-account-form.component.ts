import { Component } from '@angular/core';
import {formatMessage} from 'devextreme/localization';
import {first} from "rxjs/operators";
import notify from "devextreme/ui/notify";
import {AuthService} from "../../services";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-account-form',
  templateUrl: './create-account-form.component.html',
  styleUrls: ['./create-account-form.component.css']
})
export class CreateAccountFormComponent {
  username = '';
  email = '';
  isLoadPanelVisible = false;
  password = '';
  formatMessage = formatMessage;

  constructor(private authService: AuthService, private router: Router) { }

  createAccount(args) {
    if (!args.validationGroup.validate().isValid) {
      return;
    }
    this.isLoadPanelVisible = true;

    this.authService.createAccount(this.username, this.email, this.password)
      .pipe(first())
      .subscribe(
        data => {
          this.isLoadPanelVisible = false;
          args.validationGroup.reset();
          notify({
            message: formatMessage('Account created!', null),
            type: 'success',
            displayTime: 4000,
            position: 'top'
          });
          this.router.navigate(['/home']);
        },
        error => {
          args.validationGroup.reset();
          this.isLoadPanelVisible = false;
          notify({
            message: error.message,
            type: 'error',
            displayTime: 4000,
            position: 'top'
          });
        });
  }

  passwordComparison = () => {
    return this.password;
  }
}
