import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from '../models/topic.model';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TopicsService {
  constructor(private http: HttpClient) {}

  getTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.baseUrl}/topics`);
  }

  getTopicsSubscriptions(): Observable<Topic[]> {
    return this.http.get<Topic[]>(
      `${environment.baseUrl}/topics/subscriptions`
    );
  }

  subscribe(topicId: number): Observable<Topic[]> {
    return this.http.post<Topic[]>(
      `${environment.baseUrl}/topics/${topicId}/subscribe`,
      {}
    );
  }

  unsubscribe(topicId: number): Observable<any> {
    return this.http.delete<Topic[]>(
      `${environment.baseUrl}/topics/${topicId}/unsubscribe`
    );
  }
}
