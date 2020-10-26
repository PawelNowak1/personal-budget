import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {SideNavInnerToolbarModule} from './layouts';
import {HttpClientModule} from '@angular/common/http';
import {LoginFormModule} from './shared/components';
import {
  DxButtonModule,
  DxCheckBoxModule, DxScrollViewModule,
  DxTextBoxModule,
  DxValidationGroupModule,
  DxValidatorModule
} from 'devextreme-angular';
import {AuthService} from './shared/services';
import {AppInfoService} from './shared/services/app-info.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SideNavInnerToolbarModule,
    HttpClientModule,
    DxValidationGroupModule,
    DxTextBoxModule,
    DxValidatorModule,
    DxCheckBoxModule,
    DxButtonModule,
    LoginFormModule,
    DxScrollViewModule
  ],
  providers: [AuthService, AppInfoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
