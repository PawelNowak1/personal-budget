import {NgModule} from '@angular/core';
import {AddAccountComponent} from './popups/add-account/add-account.component';
import {
    DxButtonModule,
    DxFormModule,
    DxNumberBoxModule,
    DxPopupModule,
    DxSelectBoxModule,
    DxSwitchModule, DxTextAreaModule,
    DxTextBoxModule, DxValidationGroupModule, DxValidatorModule
} from 'devextreme-angular';
import { AddOperationComponent } from './popups/add-operation/add-operation.component';

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
        DxTextAreaModule
    ],
  declarations: [AddAccountComponent, AddOperationComponent],
  exports: [AddAccountComponent, AddOperationComponent]
})
export class SharedModule {

}
