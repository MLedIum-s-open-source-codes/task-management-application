import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public isAuth$ = new BehaviorSubject<boolean>(false);
  //public token;
  //public role;
  //public userData;

  constructor(private http: HttpClient, private router: Router) { }



}


