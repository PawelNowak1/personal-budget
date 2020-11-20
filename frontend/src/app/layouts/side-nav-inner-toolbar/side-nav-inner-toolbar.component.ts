import {Component, NgModule, OnInit} from '@angular/core';
import {
  DxButtonModule,
  DxDrawerModule,
  DxScrollViewModule,
  DxToolbarModule,
  DxTreeViewModule
} from 'devextreme-angular';
import {navigation} from '../../app-navigation';
import {Router} from '@angular/router';
import {formatMessage} from 'devextreme/localization';
import {AuthService} from "../../shared/services";

@Component({
  selector: 'app-side-nav-inner-toolbar',
  templateUrl: './side-nav-inner-toolbar.component.html',
  styleUrls: ['./side-nav-inner-toolbar.component.css']
})
export class SideNavInnerToolbarComponent implements OnInit {

  items: { text: string; path: string; icon: string; }[];
  formatMessage = formatMessage;

  constructor(private router: Router, private authService: AuthService) {
    this.items = navigation;
  }

  ngOnInit(): void {
  }

  changeNavigation(event) {
    const path = event.itemData.path;
    this.router.navigate([path]);
  }

  logOut() {
    this.authService.logOut();
  }

}

@NgModule({
  imports: [
    DxDrawerModule,
    DxToolbarModule,
    DxTreeViewModule,
    DxScrollViewModule,
    DxButtonModule
  ],
  exports: [ SideNavInnerToolbarComponent ],
  declarations: [ SideNavInnerToolbarComponent ]
})
export class SideNavInnerToolbarModule { }
