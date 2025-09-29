import { Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layouts/layout/layout.component';
import { HomeComponent } from './features/home/home.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        component: HomeComponent,
      },
    ],
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./features/auth/auth.routes').then((r) => r.AUTH_ROUTES),
  },
];
