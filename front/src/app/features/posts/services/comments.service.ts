import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../../../shared/components/comments/models/comment.model';
import { map, Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { CommentRequest } from '../models/commentRequest.model';

@Injectable({
  providedIn: 'root',
})
export class CommentsService {
  constructor(private http: HttpClient) {}

  getCommentsByPost(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(
      `${environment.baseUrl}/posts/${postId}/comments`
    );
  }

  addCommentToPost(
    postId: number,
    commentRequest: CommentRequest
  ): Observable<Comment> {
    return this.http.post<Comment>(
      `${environment.baseUrl}/posts/${postId}/comments`,
      commentRequest
    );
  }

  sortComments(
    postId: number,
    isDescending: boolean = false
  ): Observable<Comment[]> {
    return this.getCommentsByPost(postId).pipe(
      map((comments) => {
        const sorted = [...comments].sort((commentA, commentB) => {
          const dateA = new Date(commentA.createdAt);
          const dateB = new Date(commentB.createdAt);

          if (dateA.getTime() === dateB.getTime()) return 0;

          return isDescending
            ? dateB.getTime() - dateA.getTime()
            : dateA.getTime() - dateB.getTime();
        });

        return sorted;
      })
    );
  }
}
