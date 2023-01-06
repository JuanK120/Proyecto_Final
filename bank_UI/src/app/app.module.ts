import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule,FormBuilder } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ClientsComponent } from './clients/clients.component';
import { AboutDevComponent } from './about-dev/about-dev.component';
import { UsersComponent } from './users/users.component';
import { TransactionComponent } from './transaction/transaction.component';
import { ProductComponent } from './product/product.component';
import { CommonModule } from '@angular/common';
import {DatePipe} from '@angular/common';
import { ClientInfoComponent } from './client-info/client-info.component';
import { DepositWithdrawalFormComponent } from './transactionForms/deposit-withdrawal-form/deposit-withdrawal-form.component';
import { TransferFormComponent } from './transactionForms/transfer-form/transfer-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ClientsComponent,
    AboutDevComponent,
    UsersComponent,
    ProductComponent,
    ClientInfoComponent,
    TransactionComponent,
    DepositWithdrawalFormComponent,
    TransferFormComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
