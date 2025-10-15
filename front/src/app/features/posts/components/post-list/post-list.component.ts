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
  isDescending: boolean = true;

  constructor(private postsService: PostsService, private router: Router) {}

  ngOnInit(): void {
    this.posts$ = this.postsService.sortPosts(this.isDescending);
  }

  onCreatePost() {
    this.router.navigateByUrl('/posts/create');
  }

  onSort(): void {
    this.isDescending = !this.isDescending;
    this.posts$ = this.postsService.sortPosts(this.isDescending);
  }
}
