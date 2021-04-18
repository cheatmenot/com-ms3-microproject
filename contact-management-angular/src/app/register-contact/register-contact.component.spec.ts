import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterContactComponent } from './register-contact.component';

describe('RegisterContactComponent', () => {
  let component: RegisterContactComponent;
  let fixture: ComponentFixture<RegisterContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterContactComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
