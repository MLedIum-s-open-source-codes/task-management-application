import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({});

  constructor(private auth: AuthService, private user: UserService) { }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      username: new FormControl('',
        [
          Validators.required,
          Validators.maxLength(32),
          Validators.minLength(3)
        ]),
      password: new FormControl('',
        [
          Validators.required,
          Validators.maxLength(32),
          Validators.minLength(8)
        ]),
      confirmPassword: new FormControl('')
    });
  }

  onSubmit(): void {
    const registerValue = this.registerForm.value;
    if (registerValue.username.length < 4) {

    }
    if (registerValue.password.length < 8) {

    }
    if (registerValue.password != registerValue.confirmPassword) {
      console.log(1);
    } else {
      this.auth.registerUser(registerValue.username, registerValue.password).subscribe(
        data => {
          const token = data.token;



        },
        error => {
          alert(error.message);
        });
    }
  }

}
