import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { users } from './shared/models/users';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersServiceService {


  public url:string = environment.apiUrl+'/user';

  private currentUser:users|null=null;

  constructor(public http:HttpClient) { 
  };

  httpOptions = {
    headers : new HttpHeaders({
      "Content-Type" : "aplication/json"
    })
  }

  
  public getAllData(): Observable<users>{
    return this.http.get<users>(`${this.url}`)
  }

  public getIdData(id:string): Observable<users>{
    console.log(this.url)
    return this.http.get<users>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<users>{
    return this.http.post<users>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<users>{
    return this.http.put<users>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<users>{
    return this.http.delete<users>(`${this.url}/${id}`)
  }

  public getlogin(body:JSON): Observable<users>{
    return this.http.put<users>(`${this.url}/login`,body)
  }

  public setCurrentUser(user:users):void{
    this.currentUser=user;
  };

  public getCurrentUser():users|null{
    return this.currentUser;
  }

  public flushCurrentUser():void{
    this.currentUser=null;
  }

}

