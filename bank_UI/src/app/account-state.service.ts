import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { accountState } from './shared/models/accountState'; 
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountStateService {

  public url:string = environment.apiUrl+'/accountState';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<Array<accountState>>{
    return this.http.get<Array<accountState>>(`${this.url}`)
  }

  public getIdData(id:string): Observable<accountState>{
    return this.http.get<accountState>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<accountState>{
    return this.http.post<accountState>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<accountState>{
    return this.http.put<accountState>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<accountState>{
    return this.http.delete<accountState>(`${this.url}/${id}`)
  }
}
