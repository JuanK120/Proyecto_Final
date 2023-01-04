import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UsersServiceService } from '../users-service.service';
import { Router } from '@angular/router';
import { users } from '../shared/models/users';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginError=false;

  constructor(
    private usersService: UsersServiceService,
    private formBuilder:FormBuilder,
    private router:Router
  ){};

  loginForm = this.formBuilder.group({
    email : '',
    password : ''
  });

  onSubmit() : void {

    this.loginError=false;
    
    let formData:users = {
      userId:0,
      userName: null,
      email: this.loginForm.controls['email'].value as string,
      password: this.loginForm.controls['password'].value as string ,
      active: false,
    };
    let resData:users = {
      userId:0,
      userName: null,
      email: "",
      password: "" ,
      active: false,
    };
    this.usersService.getlogin(JSON.parse(JSON.stringify(formData))).subscribe(res => {
      console.log(res);
      this.usersService.setCurrentUser(res)
      this.router.navigate(['/users']);
    },err => this.loginError=true);
  };

  ngOnInit(): void {
    document.body.style.backgroundColor='rgba(47, 171, 26, 1)'
  }

}
