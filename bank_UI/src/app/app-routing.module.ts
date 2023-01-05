import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AboutDevComponent } from './about-dev/about-dev.component'
import { ClientsComponent } from './clients/clients.component';
import { UsersComponent } from './users/users.component';
import { transition } from '@angular/animations';
import { TransactionComponent } from './transaction/transaction.component';

const routes: Routes = [
  {path : '', redirectTo:"aboutDev", pathMatch:'full'},
  {path : 'login', component:LoginComponent},
  {path : 'aboutDev', component:AboutDevComponent},
  {path : 'users', component:UsersComponent},
  {path : 'clients', loadChildren:() => import('./clients/clients.module').then(m => m.ClientsModule)},
  {path : 'transactions', component:TransactionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
