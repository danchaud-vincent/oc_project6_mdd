import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../models/loginRequest.model';
import { RegisterRequest } from '../models/registerRequest.model';
import { environment } from '../../../../environments/environment';
import { UserSessionInfo } from '../../../core/models/userSessionInfo.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(loginRequest: LoginRequest): Observable<UserSessionInfo> {
    return this.http.post<UserSessionInfo>(
      `${environment.baseUrl}/auth/login`,
      loginRequest
    );
  }

  register(registerRequest: RegisterRequest): Observable<string> {
    return this.http.post<string>(
      `${environment.baseUrl}/auth/register`,
      registerRequest
    );
  }
}
