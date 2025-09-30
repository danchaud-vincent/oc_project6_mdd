import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { LoginRequest } from '../../models/loginRequest.model';
import { AuthService } from '../../services/auth.service';
import { tap } from 'rxjs';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatInputModule, MatFormFieldModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  emailRegex!: RegExp;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(this.emailRegex)]],
      password: [null, Validators.required],
    });
  }

  onLogin(): void {
    const loginRequest: LoginRequest = this.loginForm.value as LoginRequest;
    // console.log(this.loginForm.value);

    this.authService
      .login(loginRequest)
      .pipe(tap((value) => console.log(value)))
      .subscribe();
  }
}
