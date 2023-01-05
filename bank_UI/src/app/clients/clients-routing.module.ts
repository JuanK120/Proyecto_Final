import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientInfoComponent } from '../client-info/client-info.component';
import { ClientsComponent } from './clients.component';

const routes: Routes = [
  {path:"",component:ClientsComponent},
  {path:"clientInfo",component:ClientInfoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientsRoutingModule { }
