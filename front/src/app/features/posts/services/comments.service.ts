import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../../../shared/components/comments/models/comment.model';
import { Observable } from 'rxjs';
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
}
