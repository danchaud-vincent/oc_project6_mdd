import { Routes } from '@angular/router';
import { LayoutWithHeadersComponent } from './core/components/layouts/layout-with-headers/layout-with-headers.component';
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
];
