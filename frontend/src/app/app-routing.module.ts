import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {CategoriesComponent} from './pages/categories/categories.component';
import {ReportsComponent} from './pages/reports/reports.component';
import {OperationsComponent} from './pages/operations/operations.component';
import {DxCalendarModule, DxDateBoxModule} from 'devextreme-angular';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'categories',
    component: CategoriesComponent
  },
  {
    path: 'reports',
    component: ReportsComponent
  },
  {
    path: 'operations',
    component: OperationsComponent
  },
  {
    path: '**',
    redirectTo: 'home'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), DxCalendarModule, DxDateBoxModule],
  exports: [RouterModule],
  declarations: [HomeComponent, CategoriesComponent, ReportsComponent, OperationsComponent]
})
export class AppRoutingModule { }
