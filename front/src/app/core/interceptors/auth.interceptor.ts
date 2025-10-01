import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../../features/auth/services/auth.service';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const headers = new HttpHeaders().append(
      'Authorization',
      `Bearer ${'test'}`
    );

    const modifiedReq = req.clone({ headers: headers });

    return next.handle(modifiedReq);
  }
}
