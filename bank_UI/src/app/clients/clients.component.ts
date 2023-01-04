import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ClientsServiceService } from '../clients-service.service';
import { client } from '../shared/models/client';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css']
})
export class ClientsComponent {

  clients:Array<client>=[];

  constructor(
    private clientService: ClientsServiceService,
    private datePipe:DatePipe
  ){
  }

  ngOnInit(): void {
    document.body.style.backgroundColor='white'

    this.clientService.getAllData().subscribe(res =>this.clients=res);

    console.log(this.clients[0].idType.type);
  }
}
