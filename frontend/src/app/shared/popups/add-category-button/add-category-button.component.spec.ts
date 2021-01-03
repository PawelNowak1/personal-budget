import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCategoryButtonComponent } from './add-category-button.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('AddCategoryButtonComponent', () => {
  let component: AddCategoryButtonComponent;
  let fixture: ComponentFixture<AddCategoryButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCategoryButtonComponent ],
      imports: [ HttpClientTestingModule],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCategoryButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
