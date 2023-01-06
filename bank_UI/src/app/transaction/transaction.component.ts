import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { DepositWithdrawalFormComponent } from '../transactionForms/deposit-withdrawal-form/deposit-withdrawal-form.component';
import { TransferFormComponent } from '../transactionForms/transfer-form/transfer-form.component';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent {

  constructor(
    private formbuilder:FormBuilder
  ){}

  transactionType=this.formbuilder.group({
    type:""
  })

  ngOnInit(): void {
    document.body.style.backgroundColor='white'
  }

}
