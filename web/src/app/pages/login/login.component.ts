import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = new FormGroup({});

  constructor(private auth: AuthService, private user: UserService) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    });
  }

  onSubmit(): void {
    const loginValue = this.loginForm.value;
    this.auth.loginUser(loginValue.username, loginValue.password).subscribe(
      data => {
        const token = data.token;



      },
      error => {
        alert(error.message);
      });
  }

}
