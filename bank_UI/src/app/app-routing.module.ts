import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AboutDevComponent } from './about-dev/about-dev.component'

const routes: Routes = [
  {path : '', redirectTo:"aboutDev", pathMatch:'full'},
  {path : 'login', component:LoginComponent},
  {path : 'aboutDev', component:AboutDevComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
