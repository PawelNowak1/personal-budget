import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOperationComponent } from './add-operation.component';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('AddOperationComponent', () => {
  let component: AddOperationComponent;
  let fixture: ComponentFixture<AddOperationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOperationComponent ],
      imports: [ HttpClientTestingModule],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddOperationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
