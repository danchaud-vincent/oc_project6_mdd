import { Component, OnInit } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Post } from '../../models/post.model';
import { PostsService } from '../../services/posts.service';
import { AsyncPipe } from '@angular/common';
import { PostListItemComponent } from '../post-list-item/post-list-item.component';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-post-list',
  imports: [AsyncPipe, PostListItemComponent, MatIconModule, FormsModule],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.scss',
})
export class PostListComponent implements OnInit {
  posts$!: Observable<Post[]>;
  filterBy!: string;
  sort: boolean = false;

  constructor(private postsService: PostsService, private router: Router) {}

  ngOnInit(): void {
    this.posts$ = this.postsService.getPosts();
  }

  onCreatePost() {
    this.router.navigateByUrl('/posts/create');
  }

  onSort(): void {
    this.posts$ = this.postsService.getPosts().pipe(
      map((posts) => {
        const sorted = [...posts].sort((postA, postB) => {
          const dateA = new Date(postA.createdAt);
          const dateB = new Date(postB.createdAt);

          if (dateA.getTime() === dateB.getTime()) return 0;

          return this.sort
            ? dateB.getTime() - dateA.getTime()
            : dateA.getTime() - dateB.getTime();
        });

        return sorted;
      })
    );

    this.sort = !this.sort;
  }
}
