import { Component } from '@angular/core';
import { ClientsServiceService } from '../clients-service.service';
import { FormBuilder } from '@angular/forms';
import { IdTypeService } from '../id-type.service';
import { UsersServiceService } from '../users-service.service';
import { users } from '../shared/models/users';
import { client } from '../shared/models/client';
import { product } from '../shared/models/product';
import { productType } from '../shared/models/productType';
import { accountState } from '../shared/models/accountState'
import { ProductsServiceService } from '../products-service.service';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent {

  currentUser:users|null=null;

  curentClient:client|null=null;

  accounts:Array<product>=[];

  constructor(
    private clientService: ClientsServiceService,
    private formBuilder:FormBuilder,
    private idTypeService:IdTypeService,
    private usersService:UsersServiceService,
    private productService:ProductsServiceService
  ){
  }

  searchAccountForm=this.formBuilder.group(
    {
      IdSearch:''
    }
  )

  ngOnInit(): void {
    document.body.style.backgroundColor='white'
    this.curentClient=this.clientService.getCurrentClient();
    this.currentUser=this.usersService.getCurrentUser();
    this.productService.getAllData().subscribe(res => {
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

  }
}
