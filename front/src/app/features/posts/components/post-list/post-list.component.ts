import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../../models/post.model';
import { PostsService } from '../../services/posts.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-post-list',
  imports: [AsyncPipe],
  templateUrl: './post-list.component.html',
  styleUrl: './post-list.component.scss',
})
export class PostListComponent implements OnInit {
  posts$!: Observable<Post[]>;

  constructor(private postsService: PostsService) {}

  ngOnInit(): void {
    this.posts$ = this.postsService.getPosts();
  }
}
