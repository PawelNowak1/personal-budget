import {Component, NgModule, OnInit, ViewChild} from '@angular/core';
import {
  DxButtonModule,
  DxDrawerModule,
  DxScrollViewModule,
  DxToolbarModule, DxTreeViewComponent,
  DxTreeViewModule
} from 'devextreme-angular';
import {navigation} from '../../app-navigation';
import {Router} from '@angular/router';
import {formatMessage} from 'devextreme/localization';
import {AuthService} from '../../shared/services';
import {SharedModule} from "../../shared/shared.module";

@Component({
  selector: 'app-side-nav-inner-toolbar',
  templateUrl: './side-nav-inner-toolbar.component.html',
  styleUrls: ['./side-nav-inner-toolbar.component.css']
})
export class SideNavInnerToolbarComponent implements OnInit {
  @ViewChild(DxTreeViewComponent, { static: false }) menu: DxTreeViewComponent;

  items: { text: string; path: string; icon: string; }[];
  formatMessage = formatMessage;

  menuOpened = true;

  constructor(private router: Router, private authService: AuthService) {
    this.items = navigation;
  }

  ngOnInit(): void {
  }

  changeNavigation(event) {
    const path = event.itemData.path;
    this.menu.instance.collapseAll();
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
    DxButtonModule,
    SharedModule
  ],
  exports: [ SideNavInnerToolbarComponent ],
  declarations: [ SideNavInnerToolbarComponent ]
})
export class SideNavInnerToolbarModule { }
