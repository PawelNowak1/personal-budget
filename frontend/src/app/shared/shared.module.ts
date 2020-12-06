import {NgModule} from '@angular/core';
import {AddAccountComponent} from './popups/add-account/add-account.component';
import {
    DxButtonModule,
    DxFormModule,
    DxNumberBoxModule,
    DxPopupModule,
    DxSelectBoxModule, DxSpeedDialActionModule,
    DxSwitchModule, DxTextAreaModule,
    DxTextBoxModule, DxToolbarModule, DxValidationGroupModule, DxValidatorModule
} from 'devextreme-angular';
import { AddOperationComponent } from './popups/add-operation/add-operation.component';
import { AddCategoryButtonComponent } from './popups/add-category-button/add-category-button.component';
import {CommonModule} from '@angular/common';
import { HeaderComponent } from './components/header/header.component';

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
        CommonModule,
        DxToolbarModule
    ],
  declarations: [AddAccountComponent, AddOperationComponent, AddCategoryButtonComponent, HeaderComponent],
  exports: [AddAccountComponent, AddOperationComponent, AddCategoryButtonComponent, HeaderComponent]
})
export class SharedModule {

}
