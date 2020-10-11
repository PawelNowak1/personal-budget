import {Component, NgModule, OnInit} from '@angular/core';
import {DxDrawerModule, DxScrollViewModule, DxToolbarModule, DxTreeViewModule} from 'devextreme-angular';
import {navigation} from '../../app-navigation';

@Component({
  selector: 'app-side-nav-inner-toolbar',
  templateUrl: './side-nav-inner-toolbar.component.html',
  styleUrls: ['./side-nav-inner-toolbar.component.css']
})
export class SideNavInnerToolbarComponent implements OnInit {

  items: { text: string; path: string; icon: string; }[];

  constructor() {
    this.items = navigation;
  }

  ngOnInit(): void {
    console.log('H');
  }


}

@NgModule({
  imports: [
    DxDrawerModule,
    DxToolbarModule,
    DxTreeViewModule,
    DxScrollViewModule
  ],
  exports: [ SideNavInnerToolbarComponent ],
  declarations: [ SideNavInnerToolbarComponent ]
})
export class SideNavInnerToolbarModule { }
