import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Post } from '../../models/post.model';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-list-item',
  imports: [MatCardModule, DatePipe],
  templateUrl: './post-list-item.component.html',
  styleUrl: './post-list-item.component.scss',
})
export class PostListItemComponent {
  @Input() post!: Post;

  constructor(private router: Router) {}

  viewSinglePost(): void {
    console.log('View a single post', this.post);
    this.router.navigateByUrl(`/posts/${this.post.id}`);
  }
}
