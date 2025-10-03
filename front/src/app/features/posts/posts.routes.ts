import { Routes } from '@angular/router';
import { PostListComponent } from './components/post-list/post-list.component';
import { LayoutWithHeadersComponent } from '../../core/components/layouts/layout-with-headers/layout-with-headers.component';
import { SinglePostComponent } from './components/single-post/single-post.component';

export const POSTS_ROUTES: Routes = [
  {
    path: '',
    component: LayoutWithHeadersComponent,
    children: [
      {
        path: '',
        component: PostListComponent,
      },
      {
        path: ':id',
        component: SinglePostComponent,
      },
    ],
  },
];
