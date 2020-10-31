import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {SideNavInnerToolbarModule} from './layouts';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
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
import {AuthInterceptor} from './shared/interceptors/auth-interceptor';

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
  providers: [AuthService, AppInfoService, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
