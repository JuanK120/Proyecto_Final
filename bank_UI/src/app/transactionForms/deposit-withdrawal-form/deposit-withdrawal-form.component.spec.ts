import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepositWithdrawalFormComponent } from './deposit-withdrawal-form.component';

describe('DepositWithdrawalFormComponent', () => {
  let component: DepositWithdrawalFormComponent;
  let fixture: ComponentFixture<DepositWithdrawalFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepositWithdrawalFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepositWithdrawalFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
