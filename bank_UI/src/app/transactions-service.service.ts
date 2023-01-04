import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { transaction } from './shared/models/transaction';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionsServiceService {

  public url:string = environment.apiUrl+'/transaction';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<transaction>{
    return this.http.get<transaction>(`${this.url}`)
  }

  public getIdData(id:string): Observable<transaction>{
    return this.http.get<transaction>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<transaction>{
    return this.http.post<transaction>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<transaction>{
    return this.http.put<transaction>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<transaction>{
    return this.http.delete<transaction>(`${this.url}/${id}`)
  }
}