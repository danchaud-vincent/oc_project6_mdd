import { Component, OnInit } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import { Post } from '../../models/post.model';
import { catchError, Observable, of, switchMap } from 'rxjs';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute } from '@angular/router';
import { AsyncPipe, DatePipe } from '@angular/common';
import { CommentsListComponent } from '../../../../shared/components/comments/components/comments-list/comments-list.component';
import { CommentsService } from '../../services/comments.service';
import { Comment } from '../../../../shared/components/comments/models/comment.model';
import { CommentRequest } from '../../models/commentRequest.model';

@Component({
  selector: 'app-single-post',
  imports: [
    ButtonBackwardComponent,
    AsyncPipe,
    DatePipe,
    CommentsListComponent,
  ],
  templateUrl: './single-post.component.html',
  styleUrl: './single-post.component.scss',
})
export class SinglePostComponent implements OnInit {
  post$!: Observable<Post>;
  comments$!: Observable<Comment[]>;

  constructor(
    private postsService: PostsService,
    private commentsService: CommentsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const postId = this.route.snapshot.params['id'];
    this.post$ = this.postsService.getPostById(postId);
    this.comments$ = this.commentsService.getCommentsByPost(postId);
  }

  onPostCommented(newCommentValue: string) {
    const postId = this.route.snapshot.params['id'];
    const commentRequest: CommentRequest = { content: newCommentValue };

    this.comments$ = this.commentsService
      .addCommentToPost(postId, commentRequest)
      .pipe(
        switchMap(() => this.commentsService.getCommentsByPost(postId)),
        catchError((err) => {
          console.error("Erreur lors de l'ajout du commentaire: ", err);
          return of([]);
        })
      );
  }
}
