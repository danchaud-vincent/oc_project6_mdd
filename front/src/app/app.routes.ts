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
  {
    path: 'posts',
    loadChildren: () =>
      import('./features/posts/posts.routes').then((r) => r.POSTS_ROUTES),
  },
  {
    path: 'topics',
    loadChildren: () =>
      import('./features/topics/topics.routes').then((r) => r.TOPICS_ROUTES),
  },
  {
    path: 'me',
    loadChildren: () =>
      import('./features/me/me.routes').then((r) => r.ME_ROUTES),
  },
];
