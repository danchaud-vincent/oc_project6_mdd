import { Component, Input, OnInit } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import { Post } from '../../models/post.model';
import { Observable } from 'rxjs';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute } from '@angular/router';
import { AsyncPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-single-post',
  imports: [ButtonBackwardComponent, AsyncPipe, DatePipe],
  templateUrl: './single-post.component.html',
  styleUrl: './single-post.component.scss',
})
export class SinglePostComponent implements OnInit {
  post$!: Observable<Post>;

  constructor(
    private postsService: PostsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const postId = this.route.snapshot.params['id'];
    this.post$ = this.postsService.getPostById(postId);
  }
}
