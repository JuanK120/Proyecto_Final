import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { timestamp } from 'rxjs';
import { ClientsServiceService } from '../clients-service.service';
import { IdTypeService } from '../id-type.service';
import { client } from '../shared/models/client';
import { identificationType } from '../shared/models/identificationType';
import { users } from '../shared/models/users';
import { UsersServiceService } from '../users-service.service';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent {

  clients:Array<client>=[];

  idTypes:Array<identificationType>=[];

  constructor(
    private clientService: ClientsServiceService,
    private formBuilder:FormBuilder,
    private idTypeService:IdTypeService,
    private usersService:UsersServiceService,
  ){
  }


  searchClientForm = this.formBuilder.group(
    {
      IdSearch: ""
    }
  )

  addClientForm = this.formBuilder.group(
    {
      idType: {},
      idNumber:-10000,
      name:"",
      lastName:"",
      email:"",
      birthDate:new Date("0000-01-01"),
    }
  )

  EditClientForm = this.formBuilder.group(
    {
      clientId:-1,
      idType: {},
      idNumber:-10000,
      name:"",
      lastName:"",
      email:"",
      birthDate:new Date(""),
      creationDate:new Date(""),
      creationUser:{},
      modificationDate:new Date(""),
      modificationUser:{},
      active:false   
    }
  )


  ngOnInit(): void {
    document.body.style.backgroundColor='white'
    this.clientService.getAllData().subscribe(res =>this.clients=res);
    this.idTypeService.getAllData().subscribe(res =>this.idTypes=res);
  }

  refresh():void{
    this.clientService.getAllData().subscribe(res =>this.clients=res);
  }

  showAddClientForm(): void{    
    this.idTypeService.getAllData().subscribe(res =>this.idTypes=res);
    document.querySelector("#addClientForm")!.classList.add("active");
  }
  hideAddClientMenu():void{
    document.querySelector("#addClientForm")!.classList.remove("active");
  }

  showEditClientForm(client:client): void{
    this.EditClientForm.controls["clientId"].setValue(client.clientId);
    this.EditClientForm.controls["idType"].setValue(client.idType);
    this.EditClientForm.controls["idNumber"].setValue(client.idNumber);
    this.EditClientForm.controls["name"].setValue(client.name);
    this.EditClientForm.controls["lastName"].setValue(client.lastName);
    this.EditClientForm.controls["email"].setValue(client.email);
    this.EditClientForm.controls["birthDate"].setValue(new Date(client.birthDate));
    this.EditClientForm.controls["creationDate"].setValue(client.creationDate);
    this.EditClientForm.controls["creationUser"].setValue(client.creationUser);
    this.EditClientForm.controls["modificationDate"].setValue(client.modificationDate);
    this.EditClientForm.controls["modificationUser"].setValue(client.modificationUser);
    this.EditClientForm.controls["active"].setValue(client.active); 
    this.idTypeService.getAllData().subscribe(res =>this.idTypes=res);
    document.querySelector("#editClientForm")!.classList.add("active");
    console.log("here");
    
  }
  hideEditClientMenu():void{
    document.querySelector("#editClientForm")!.classList.remove("active");
  }

  searchClient():void{
    let search = this.searchClientForm.controls["IdSearch"].value as string;
    if (search == "" || search == null){
      this.clientService.getAllData().subscribe(res =>this.clients=res);
    } else {
      this.clientService.getIdData(this.searchClientForm.controls["IdSearch"].value as string).subscribe(res =>{
        this.clients=[res];
      },err => alert("Client Not Found"));
    }
    
  }

  addClient(): void{
    let newClient:client= {
      clientId:-1,
      idType:this.addClientForm.controls["idType"].value as identificationType,
      idNumber:this.addClientForm.controls["idNumber"].value as number,
      name:this.addClientForm.controls["name"].value as string,
      lastName:this.addClientForm.controls["lastName"].value as string,
      email:this.addClientForm.controls["email"].value as string,
      birthDate:this.addClientForm.controls["birthDate"].value as Date,
      creationDate:new Date(),
      creationUser:this.usersService.getCurrentUser() as users,
      modificationDate:new Date(),
      modificationUser:this.usersService.getCurrentUser() as users,
      active:true
    }
    this.clientService.postData(JSON.parse(JSON.stringify(newClient))).subscribe(res => {
      console.log(res);
      this.clientService.getAllData().subscribe(res =>this.clients=res);
      alert("Client Created");
    },err => alert("invalid Client Information"));
  }

  expandClient(client:client):void{
    this.clientService.setCurrentClient(client);
  }

  editClient(): void{
    let newClient:client= {
      clientId:this.EditClientForm.controls["clientId"].value as number,
      idType:this.EditClientForm.controls["idType"].value as identificationType,
      idNumber:this.EditClientForm.controls["idNumber"].value as number,
      name:this.EditClientForm.controls["name"].value as string,
      lastName:this.EditClientForm.controls["lastName"].value as string,
      email:this.EditClientForm.controls["email"].value as string,
      birthDate:this.EditClientForm.controls["birthDate"].value as Date,
      creationDate:this.EditClientForm.controls["creationDate"].value as Date,
      creationUser:this.EditClientForm.controls["creationUser"].value  as users,
      modificationDate:new Date(),
      modificationUser:this.usersService.getCurrentUser() as users,
      active:true
    }
    console.log(newClient)
    this.clientService.updateData(JSON.parse(JSON.stringify(newClient))).subscribe(res => {
      console.log(res);
      this.clientService.getAllData().subscribe(res =>this.clients=res);
      alert("Client Information Modified")
    },err => alert("invalid Client Information"));
    
  }

  deleteClient(client:client):void{
    this.clientService.deleteData(`${client.clientId}`).subscribe(res => {
      console.log(res);
      this.clientService.getAllData().subscribe(res =>this.clients=res);
      alert("Client deleted")
    },err => alert("Client Could Not Be Deleted"));
  }

  
}
