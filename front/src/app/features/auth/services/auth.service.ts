import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../models/loginRequest.model';
import { RegisterRequest } from '../models/registerRequest.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(loginRequest: LoginRequest): Observable<string> {
    return this.http.post<string>(
      'http://localhost:3001/api/auth/login',
      loginRequest
    );
  }

  register(registerRequest: RegisterRequest): Observable<string> {
    return this.http.post<string>(
      'http://localhost:3001/api/auth/register',
      registerRequest
    );
  }
}
