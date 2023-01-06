import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { product } from './shared/models/product';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsServiceService {

  public url:string = environment.apiUrl+'/product';

  constructor(public http:HttpClient) { };

  public getAllData(): Observable<Array<product>>{
    return this.http.get<Array<product>>(`${this.url}`)
  }

  public getIdData(id:string): Observable<product>{
    return this.http.get<product>(`${this.url}/${id}`)
  }

  public postData(body:JSON): Observable<product>{
    return this.http.post<product>(`${this.url}`,body)
  }

  public updateData(body:JSON): Observable<product>{
    return this.http.put<product>(`${this.url}`,body)
  }

  public deleteData(id:string): Observable<product>{
    return this.http.delete<product>(`${this.url}/${id}`)
  }

  public getDataByOwner(id:string): Observable<Array<product>>{
    return this.http.get<Array<product>>(`${this.url}/owner/${id}`)
  }

  public getDataByProductNumber(id:string): Observable<Array<product>>{
    return this.http.get<Array<product>>(`${this.url}/number/${id}`)
  }

  public exemptAccount(id:string, body:JSON): Observable<product>{
    return this.http.put<product>(`${this.url}/exempt/${id}`,body)
  }

  public CancelAccount(id:string,body:JSON): Observable<product>{
    return this.http.put<product>(`${this.url}/cancelAccount/${id}`,body)
  }
}
