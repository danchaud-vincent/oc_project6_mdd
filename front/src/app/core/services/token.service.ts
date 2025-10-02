import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  public getToken(): string {
    return sessionStorage.getItem('accessToken') || '';
  }

  public setToken(token: string): void {
    sessionStorage.setItem('accessToken', token);
  }

  public removeToken(): void {
    sessionStorage.removeItem('accessToken');
  }
}
