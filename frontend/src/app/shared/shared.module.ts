import {NgModule} from '@angular/core';
import {AddAccountComponent} from './popups/add-account/add-account.component';
import {
  DxButtonModule,
  DxFormModule,
  DxNumberBoxModule,
  DxPopupModule,
  DxSelectBoxModule, DxSpeedDialActionModule,
  DxSwitchModule, DxTextAreaModule,
  DxTextBoxModule, DxValidationGroupModule, DxValidatorModule
} from 'devextreme-angular';
import { AddOperationComponent } from './popups/add-operation/add-operation.component';
import { AddCategoryButtonComponent } from './popups/add-category-button/add-category-button.component';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [
    DxPopupModule,
    DxFormModule,
    DxTextBoxModule,
    DxSelectBoxModule,
    DxSwitchModule,
    DxNumberBoxModule,
    DxValidationGroupModule,
    DxValidatorModule,
    DxButtonModule,
    DxTextAreaModule,
    DxSpeedDialActionModule,
    CommonModule
  ],
  declarations: [AddAccountComponent, AddOperationComponent, AddCategoryButtonComponent],
  exports: [AddAccountComponent, AddOperationComponent, AddCategoryButtonComponent]
})
export class SharedModule {

}
