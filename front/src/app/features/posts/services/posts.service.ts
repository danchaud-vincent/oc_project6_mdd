import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';
import { PostRequest } from '../models/postRequest.model';

@Injectable({
  providedIn: 'root',
})
export class PostsService {
  constructor(private http: HttpClient) {}

  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.baseUrl}/posts`);
  }

  getPostById(postId: number): Observable<Post> {
    return this.http.get<Post>(`${environment.baseUrl}/posts/${postId}`);
  }

  createPost(postRequest: PostRequest): Observable<Post> {
    return this.http.post<Post>(`${environment.baseUrl}/posts`, postRequest);
  }
}
