import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { users } from './shared/models/users';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersServiceService {

  public url:string = environment.UrlApi+'/users';

  constructor(public http:HttpClient) { };

  
  public getAllData(): Observable<users>{
    return this.http.get<users>(`${this.url}`)
  }

  public getIdData(id:string): Observable<users>{
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

}

