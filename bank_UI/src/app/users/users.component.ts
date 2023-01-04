import { Component } from '@angular/core';
import { users } from '../shared/models/users';
import { UsersServiceService } from '../users-service.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  currentUser:users={
    userId:-1,
    userName:"",
    email:"",
    password:"",
    active:false
  };

  constructor(
    private usersService: UsersServiceService,
  ){
  }

  ngOnInit(): void {
    document.body.style.backgroundColor='white'

    this.currentUser=this.usersService.getCurrentUser()!;
    
  }
}
