import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { productType } from './shared/models/productType';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductTypeService {

  public url:string = environment.apiUrl+'/productType';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<Array<productType>>{
    return this.http.get<Array<productType>>(`${this.url}`)
  }

  public getIdData(id:string): Observable<productType>{
    return this.http.get<productType>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<productType>{
    return this.http.post<productType>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<productType>{
    return this.http.put<productType>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<productType>{
    return this.http.delete<productType>(`${this.url}/${id}`)
  }
}
