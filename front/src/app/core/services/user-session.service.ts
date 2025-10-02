import { Injectable } from '@angular/core';
import { UserSessionInfo } from '../models/userSessionInfo.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class UserSessionService {
  private userSessionInfo: UserSessionInfo | undefined;
  private isLogged = false;
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor(private tokenService: TokenService) {}

  public isLogged$(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public login(userInfo: UserSessionInfo): void {
    this.userSessionInfo = userInfo;
    this.tokenService.setToken(userInfo.token);
    this.isLogged = true;
    this.isLoggedSubject.next(this.isLogged);
  }

  public logout(): void {
    this.userSessionInfo = undefined;
    this.tokenService.removeToken();
    this.isLogged = false;
    this.isLoggedSubject.next(this.isLogged);
  }
}
