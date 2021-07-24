import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {UserService} from "./user.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private router: Router, private user: UserService) { }

  loginUser(username: string, password: string): Observable<any> {
    const body = {
      username: username,
      password: password
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post('auth/login', body, {headers});
  }

  registerUser(username: string, password: string): Observable<any> {
    const body = {
      username: username,
      password: password
    }
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post('auth/register', body, {headers});
  }

  logout(): void{
    localStorage.clear();
    this.router.navigate(['']);
    this.user.isAuth$.next(false);
  }
}
