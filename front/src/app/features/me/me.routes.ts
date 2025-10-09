import { Routes } from '@angular/router';
import { LayoutWithHeadersComponent } from '../../core/components/layouts/layout-with-headers/layout-with-headers.component';
import { MeComponent } from './components/me/me.component';
import { AuthGuard } from '../../core/guards/auth.guard';

export const ME_ROUTES: Routes = [
  {
    path: '',
    component: LayoutWithHeadersComponent,
    children: [
      {
        path: '',
        component: MeComponent,
        canActivate: [AuthGuard],
      },
    ],
  },
];
