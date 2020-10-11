import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideNavInnerToolbarComponent } from './side-nav-inner-toolbar.component';

describe('SideNavInnerToolbarComponent', () => {
  let component: SideNavInnerToolbarComponent;
  let fixture: ComponentFixture<SideNavInnerToolbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SideNavInnerToolbarComponent ]
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
