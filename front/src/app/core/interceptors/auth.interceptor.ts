import {
  HttpEvent,
  HttpHandlerFn,
  HttpHeaders,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { Observable } from 'rxjs';
import { UserSessionService } from '../services/user-session.service';

export function authInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
): Observable<HttpEvent<unknown>> {
  const userSessionService = inject(UserSessionService);
  const token = userSessionService.getToken();

  if (req.url.includes('/auth/login') || req.url.includes('/auth/register')) {
    return next(req);
  }

  const modifiedReq = req.clone({
    setHeaders: token ? { Authorization: `Bearer ${token}` } : {},
  });

  return next(modifiedReq);
}
