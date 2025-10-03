import { Routes } from '@angular/router';
import { LayoutWithHeadersComponent } from '../../core/components/layouts/layout-with-headers/layout-with-headers.component';
import { TopicListComponent } from './components/topic-list/topic-list.component';

export const TOPICS_ROUTES: Routes = [
  {
    path: '',
    component: LayoutWithHeadersComponent,
    children: [
      {
        path: '',
        component: TopicListComponent,
      },
    ],
  },
];
