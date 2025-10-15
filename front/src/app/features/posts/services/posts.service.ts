import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
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

  sortPosts(isDescending: boolean = false): Observable<Post[]> {
    return this.getPosts().pipe(
      map((posts) => {
        const sorted = [...posts].sort((postA, postB) => {
          const dateA = new Date(postA.createdAt);
          const dateB = new Date(postB.createdAt);

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
