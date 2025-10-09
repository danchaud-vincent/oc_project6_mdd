import { Component, OnInit } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { Topic } from '../../../topics/models/topic.model';
import { TopicsService } from '../../../topics/services/topics.service';
import { Observable, tap } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { PostsService } from '../../services/posts.service';
import { PostRequest } from '../../models/postRequest.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  imports: [
    ButtonBackwardComponent,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ButtonComponent,
    AsyncPipe,
  ],
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.scss',
})
export class AddPostComponent implements OnInit {
  addPostForm!: FormGroup;
  topics$!: Observable<Topic[]>;

  constructor(
    private formBuilder: FormBuilder,
    private topicsService: TopicsService,
    private postsService: PostsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.addPostForm = this.formBuilder.group({
      topic: [null, [Validators.required]],
      title: [null, [Validators.required]],
      content: [null, Validators.required],
    });

    this.topics$ = this.topicsService.getTopics();
  }

  onSubmitForm() {
    const formValue = this.addPostForm.value;
    const postRequest: PostRequest = {
      topicId: formValue.topic,
      title: formValue.title,
      content: formValue.content,
    };

    this.postsService
      .createPost(postRequest)
      .pipe(tap(() => this.router.navigateByUrl('/posts')))
      .subscribe();
  }
}
