import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { timestamp } from 'rxjs';
import { ClientsServiceService } from '../clients-service.service';
import { IdTypeService } from '../id-type.service';
import { UsersServiceService } from '../users-service.service';
import { users } from '../shared/models/users';
import { client } from '../shared/models/client';
import { product } from '../shared/models/product';
import { productType } from '../shared/models/productType';
import { accountState } from '../shared/models/accountState'
import { ProductsServiceService } from '../products-service.service';
import { ProductTypeService } from '../product-type.service';
import { AccountStateService } from '../account-state.service';
import { transaction } from '../shared/models/transaction';
import { TransactionsServiceService } from '../transactions-service.service';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent {

  currentUser:users|null=null;

  curentClient:client|null=null;

  accounts:Array<product>=[];

  accountTypes:Array<productType>=[];

  states:Array<accountState>=[]

  currentAccount:product|null=null;
  transactions:Array<transaction>=[];

  constructor(
    private clientService: ClientsServiceService,
    private formBuilder:FormBuilder,
    private AccountTypeService:ProductTypeService,
    private usersService:UsersServiceService,
    private accountStateService:AccountStateService,
    private productService:ProductsServiceService,
    private transactionService:TransactionsServiceService
  ){
  }

  searchAccountForm=this.formBuilder.group(
    {
      IdSearch:''
    }
  )

  addAccountForm=this.formBuilder.group({
    accountType:{},
    balance:-10000000,
    gmfExempt:false,
  })

  ngOnInit(): void {
    document.body.style.backgroundColor='white'
    this.curentClient=this.clientService.getCurrentClient();
    this.currentUser=this.usersService.getCurrentUser();
    this.AccountTypeService.getAllData().subscribe(
      res => {
        this.accountTypes=res;
      }
    )
    this.accountStateService.getAllData().subscribe(
      res => {
        this.states=res;
      }
    )
    this.productService.getDataByOwner(`${this.curentClient?.clientId}`).subscribe(res => {
      console.log(res);      
      this.accounts=res
    });

  }



  flushCurrentClient():void{
    this.clientService.flushCurrentClient();
  }

  flushAll():void{
    this.clientService.flushCurrentClient();
    this.usersService.flushCurrentUser();
  }

  searchAccount():void{
    let search = this.searchAccountForm.controls["IdSearch"].value as string;
    if (search == "" || search == null){
      this.productService.getDataByOwner(`${this.curentClient?.clientId}`).subscribe(res => {
        console.log(res);      
        this.accounts=res
      });
    } else {
      this.productService.getIdData(this.searchAccountForm.controls["IdSearch"].value as string).subscribe(res =>{
        this.accounts=[res];
      },err => alert("Account Not Found"));
    }
  }

  showAddAccountForm(): void{    
    document.querySelector("#addAccountForm")!.classList.add("active");
  }
  HideAddAccountForm():void{
    document.querySelector("#addAccountForm")!.classList.remove("active");
  }

  addAccount():void{
    let newAccount:product={
      productId:-1,
      owner:this.curentClient as client,
      productType:this.addAccountForm.controls['accountType'].value as productType,
      productNumber:-1,
      state:{
          "idState": 1,
          "stateName": "Active"
      },
      balance:this.addAccountForm.controls["balance"].value as number,
      availableBalance:-1,
      gmfExempt:this.addAccountForm.controls["gmfExempt"].value as boolean,
      creationDate:new Date(),
      creationUser:this.usersService.getCurrentUser() as users,
      modificationDate:new Date(),
      modificationUser:this.usersService.getCurrentUser() as users,
    }
    console.log(newAccount);
    this.productService.postData(JSON.parse(JSON.stringify(newAccount))).subscribe(res => {
      console.log(res);
      this.productService.getDataByOwner(`${this.curentClient?.clientId}`).subscribe(res =>this.accounts=res);
      alert("Account Created");
    },err => alert("invalid Account Information"));
  }

  exemptAccount(account:product):void{
    this.productService.exemptAccount(`${account.productId}`,JSON.parse(JSON.stringify(this.currentUser))).subscribe(res => alert("Gmf Value Updated"), 
    err=>{
      console.log('body', JSON.parse(JSON.stringify(this.currentUser)))
      console.log('error', err);      
      alert("an error occurred try again later")      
    });
    
    
  }

  cancelAccount(account:product):void{
    this.productService.exemptAccount(`${account.productId}`,JSON.parse(JSON.stringify(this.currentUser))).subscribe(res => alert("Account Cancelled"), 
    err=>alert("an error occurred try again later"));
  }

  showAccountInfo(product:product): void{    
    this.currentAccount=product;
    this.transactionService.getAllDataByAccount(`${product.productNumber}`).subscribe(res=>this.transactions=res)
    document.querySelector("#accountInfo")!.classList.add("active");
  }
  HideAccountInfo():void{
    document.querySelector("#accountInfo")!.classList.remove("active");
  }


}
