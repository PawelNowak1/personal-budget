import {NgModule} from '@angular/core';
import {AddAccountComponent} from './popups/add-account/add-account.component';
import {
  DxFormModule,
  DxNumberBoxModule,
  DxPopupModule,
  DxSelectBoxModule,
  DxSwitchModule,
  DxTextBoxModule, DxValidationGroupModule, DxValidatorModule
} from 'devextreme-angular';

@NgModule({
  imports: [
    DxPopupModule,
    DxFormModule,
    DxTextBoxModule,
    DxSelectBoxModule,
    DxSwitchModule,
    DxNumberBoxModule,
    DxValidationGroupModule,
    DxValidatorModule
  ],
  declarations: [AddAccountComponent],
  exports: [AddAccountComponent]
})
export class SharedModule {

}
