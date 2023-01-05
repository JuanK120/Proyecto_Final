import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { identificationType } from './shared/models/identificationType';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IdTypeService {
  
  public url:string = environment.apiUrl+'/identificationType';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<Array<identificationType>>{
    return this.http.get<Array<identificationType>>(`${this.url}`)
  }

  public getIdData(id:string): Observable<identificationType>{
    return this.http.get<identificationType>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<identificationType>{
    return this.http.post<identificationType>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<identificationType>{
    return this.http.put<identificationType>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<identificationType>{
    return this.http.delete<identificationType>(`${this.url}/${id}`)
  }

}
