import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideNavInnerToolbarComponent } from './side-nav-inner-toolbar.component';
import {RouterTestingModule} from '@angular/router/testing';
import {AuthService} from '../../shared/services';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('SideNavInnerToolbarComponent', () => {
  let component: SideNavInnerToolbarComponent;
  let fixture: ComponentFixture<SideNavInnerToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SideNavInnerToolbarComponent ],
      imports: [ RouterTestingModule, HttpClientTestingModule ],
      providers: [AuthService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SideNavInnerToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
