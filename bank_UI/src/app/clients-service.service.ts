import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { client } from './shared/models/client';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientsServiceService {

  public url:string = environment.UrlApi+'/client';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<client>{
    return this.http.get<client>(`${this.url}`)
  }

  public getIdData(id:string): Observable<client>{
    return this.http.get<client>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<client>{
    return this.http.post<client>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<client>{
    return this.http.put<client>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<client>{
    return this.http.delete<client>(`${this.url}/${id}`)
  }

}
