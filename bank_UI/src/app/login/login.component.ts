import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UsersServiceService } from '../users-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(
    private usersService: UsersServiceService,
    private formBuilder:FormBuilder,
  ){};

  loginForm = this.formBuilder.group({
    email : '',
    password : ''
  });

  onSubmit() : void {

  };

  ngOnInit(): void {
    document.body.style.backgroundColor='rgba(47, 171, 26, 1)'
  }

}
