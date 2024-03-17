import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl="http://localhost:8082/user/api";

  constructor(private httpClient:HttpClient) { }

  getUserList():Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseUrl}/all`); }

  createUser(user:User):Observable<object>{
    return this.httpClient.post(`${this.baseUrl}/create`,user);
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUser(id:number,user:User):Observable<object>{
    return this.httpClient.put(`${this.baseUrl}/update/${id}`,user);
  }

  deleteUser(id:number):Observable<object>{
    return this.httpClient.delete(`${this.baseUrl}/delete/${id}`);
  }

}
