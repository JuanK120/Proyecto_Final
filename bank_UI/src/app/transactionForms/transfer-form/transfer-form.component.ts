import { JsonPipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProductsServiceService } from 'src/app/products-service.service';
import { product } from 'src/app/shared/models/product';
import { transaction } from 'src/app/shared/models/transaction';
import { transactionType } from 'src/app/shared/models/transactionType';
import { users } from 'src/app/shared/models/users';
import { TransactionsServiceService } from 'src/app/transactions-service.service';
import { UsersServiceService } from 'src/app/users-service.service';

@Component({
  selector: 'app-transfer-form',
  templateUrl: './transfer-form.component.html',
  styleUrls: ['./transfer-form.component.css']
})
export class TransferFormComponent {

  @Input("transferType") transferType = "";

  transactionTypes:Array<transactionType>=[];

  currentUser:users|null=null;

  validTargetAccount:boolean=false;
  validOriginAccount:boolean=false;
  targetAccount:product|null=null;
  originAccount:product|null=null;

  newTransfer:transaction={
    transactionId:-1,
    transactionType:{
        "idType": 1,
        "transactionType": "Deposit"
    },
    movementType:{
        "idType": 2,
        "transactionType": "Debit"
    },
    movementDate:new Date(),
    amount:0,
    balance:0,
    availableBalance:0,
    description:"",
    targetProduct:{
        "productId": 0,
        "owner": {
            "clientId": 0,
            "idNumber": 0,
            "name": "",
            "lastName": "",
            "email": "",
            "birthDate": new Date(),
            "creationDate": new Date(),
            "modificationDate": new Date(),
            "active": false,
            "modificationUser": {
                "userId": 0,
                "userName": "",
                "email": "a",
                "password": "",
                "active": false
            },
            "creationUser": {
              "userId": 0,
              "userName": "",
              "email": "a",
              "password": "",
              "active": false
          },
            "idType": {
                "idType": 0,
                "type": ""
            }
        },
        "productType": {
            "idType": 0,
            "accountType": ""
        },
        "productNumber": 0,
        "state": {
            "idState": 0,
            "stateName": ""
        },
        "balance": 0,
        "availableBalance": 0,
        "gmfExempt": false,
        "creationDate": new Date(),
        "creationUser": {
            "userId": 0,
            "userName": "",
            "email": "a",
            "password": "",
            "active": false
        },
        "modificationDate": new Date(),
        "modificationUser": {
            "userId": 0,
            "userName": "",
            "email": "a",
            "password": "",
            "active": false
        }
    }
  } ;

  constructor(
    private formBuilder:FormBuilder,
    private userService:UsersServiceService,
    private productService:ProductsServiceService,
    private transerservice:TransactionsServiceService
  ){}

  transferForm=this.formBuilder.group({
    originProduct:0,
    targetProduct:0,
    description:"",
    amount:0
  })

  ngOnInit(): void {
    document.body.style.backgroundColor='white'
  }

  searchOriginAccount(){
    this.productService.getDataByProductNumber(`${this.transferForm.controls["originProduct"].value as number}`).subscribe(res=>{this.originAccount=res[0];
      this.validOriginAccount=true;
      },
      err=>{
        this.validOriginAccount=true;
        alert("Account not found");
      }
    )  
  }
  searchTargetAccount(){
    this.productService.getDataByProductNumber(`${this.transferForm.controls["targetProduct"].value as number}`).subscribe(res=>{this.targetAccount=res[0];
      this.validTargetAccount=true;
      },
      err=>{
        this.validTargetAccount=true;
        alert("Account not found");
      }
    )  
  }

  onSubmit():void{
    if (this.validTargetAccount && this.validOriginAccount){
      this.newTransfer.transactionType={
          "idType": 3,
          "transactionType": "Transfer"
      };
      this.newTransfer.movementType={
          "idType": 2,
          "transactionType": "Debit"
      };
      this.newTransfer.movementDate=new Date();
      this.newTransfer.amount=this.transferForm.controls["amount"].value as number;
      this.newTransfer.description=this.transferForm.controls["description"].value as string;
      this.newTransfer.targetProduct=this.targetAccount!;
      this.transerservice.postData(JSON.parse(JSON.stringify(this.newTransfer))).subscribe(res=>{
          },err=>{alert("Invalid Transfer");
            console.log("here");
            
          }
          )  
      this.newTransfer.transactionType={
            "idType": 3,
            "transactionType": "Transfer"
        };
      this.newTransfer.movementType={
            "idType": 1,
            "transactionType": "Credit"
        };
       this.newTransfer.movementDate=new Date();
      this.newTransfer.amount=-(this.transferForm.controls["amount"].value as number);
      this.newTransfer.description=this.transferForm.controls["description"].value as string;
      this.newTransfer.targetProduct=this.originAccount!;
      this.transerservice.postData(JSON.parse(JSON.stringify(this.newTransfer))).subscribe(res=>{alert("Successfull Operation");
        },err=>{alert("Invalid Transfer");
          console.log("here");            
        }
      )         
    } else {
      if(this.validOriginAccount){
        alert("Please Select a valid Origin Account")
      } else {
        alert("Please Select a valid Target Account")
      }
      
    }                      
  }

}
