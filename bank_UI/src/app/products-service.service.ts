import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { product } from './shared/models/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsServiceService {

  public url:string = environment.UrlApi+'/product';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<product>{
    return this.http.get<product>(`${this.url}`)
  }

  public getIdData(id:string): Observable<product>{
    return this.http.get<product>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<product>{
    return this.http.post(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<product>{
    return this.http.put(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<product>{
    return this.http.delete<product>(`${this.url}/${id}`)
  }

}
