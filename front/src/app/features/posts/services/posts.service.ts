import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../models/post.model';

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
}
