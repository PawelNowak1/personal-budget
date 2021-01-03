import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {CategoriesComponent} from './pages/categories/categories.component';
import {ReportsComponent} from './pages/reports/reports.component';
import {OperationsComponent} from './pages/operations/operations.component';
import {DevExtremeModule, DxCalendarModule, DxDateBoxModule, DxValidationGroupModule} from 'devextreme-angular';
import {LoginFormComponent} from './shared/components';
import {AuthGuardService} from './shared/services';
import {CreateAccountFormComponent} from './shared/components/create-account-form/create-account-form.component';
import {SharedModule} from './shared/shared.module';
import {AccountsComponent} from './pages/accounts/accounts.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'categories',
    component: CategoriesComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'reports',
    component: ReportsComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'operations',
    component: OperationsComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'accounts',
    component: AccountsComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'login',
    component: LoginFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: 'createAccount',
    component: CreateAccountFormComponent,
    canActivate: [ AuthGuardService ]
  },
  {
    path: '**',
    redirectTo: 'home',
    canActivate: [ AuthGuardService ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    DxCalendarModule, DxDateBoxModule, DxValidationGroupModule, DevExtremeModule, SharedModule],
  providers: [AuthGuardService],
  exports: [RouterModule],
  declarations: [HomeComponent, CategoriesComponent, ReportsComponent, OperationsComponent, AccountsComponent, CreateAccountFormComponent]
})
export class AppRoutingModule { }
