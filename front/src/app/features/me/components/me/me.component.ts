import { Component, OnInit, ViewChild } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormGroupDirective,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { passwordValidator } from '../../../auth/validators/password.validator';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { map, Observable, tap } from 'rxjs';
import { Me } from '../../models/me.model';
import { MeService } from '../../services/me.service';
import { MeUpdateRequest } from '../../models/meUpdateRequest.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TopicListComponent } from '../../../topics/components/topic-list/topic-list.component';
import { TopicListItemComponent } from '../../../topics/components/topic-list-item/topic-list-item.component';
import { Topic } from '../../../topics/models/topic.model';
import { TopicsService } from '../../../topics/services/topics.service';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-me',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    ButtonComponent,
    TopicListItemComponent,
    AsyncPipe,
  ],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss',
})
export class MeComponent implements OnInit {
  @ViewChild(FormGroupDirective) formDirective!: FormGroupDirective;

  meForm!: FormGroup;
  topicsSubscribed$!: Observable<Topic[]>;

  constructor(
    private formBuilder: FormBuilder,
    private meService: MeService,
    private topicsService: TopicsService,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    // SET UP AND FILL THE FORM
    this.meForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required, passwordValidator()]],
    });

    this.meService
      .getMe()
      .pipe(
        tap((value) =>
          this.meForm.patchValue({
            username: value.username,
            email: value.email,
          })
        )
      )
      .subscribe();

    // LOAD THE TOPICS
    this.loadTopics();
  }

  loadTopics(): void {
    this.topicsSubscribed$ = this.topicsService.getTopicsSubscriptions().pipe(
      map((topics) => {
        return topics.map((topic) => {
          return { ...topic, isSubscribed: true };
        });
      })
    );
  }

  onSubscription(topic: Topic) {
    if (topic.isSubscribed) {
      this.topicsService
        .unsubscribe(topic.id)
        .pipe(tap(() => this.loadTopics()))
        .subscribe();
    } else {
      this.topicsService
        .subscribe(topic.id)
        .pipe(tap(() => this.loadTopics()))
        .subscribe();
    }
  }

  onSubmitForm() {
    const meUpdateRequest: MeUpdateRequest = this.meForm.value;

    this.meService
      .updateMeInfo(meUpdateRequest)
      .pipe(
        tap((value) => {
          this.formDirective.resetForm({
            username: value.username,
            email: value.email,
            password: '',
          });

          this.matSnackBar.open('Mise à jour du profil', 'Close', {
            duration: 2000,
          });
        })
      )
      .subscribe();
  }

  getFormControlErrorText(ctrl: AbstractControl): string {
    if (!ctrl || !ctrl.errors) {
      return '';
    }

    if (ctrl.hasError('required')) {
      return 'Ce champs est requis';
    }

    if (ctrl.hasError('email')) {
      return "Merci d'entrer une adresse mail valide";
    }

    if (ctrl.hasError('minlength')) {
      return `Ce champs doit contenir au moins ${ctrl.errors['minlength'].requiredLength}`;
    }

    if (
      ctrl.hasError('missingUpperCase') ||
      ctrl.hasError('missingLowerCase') ||
      ctrl.hasError('missingNumber') ||
      ctrl.hasError('missingSpecialChar')
    ) {
      return 'Le mot de passe doit contenir une majuscule, une minuscule, un chiffre et un caractère spécial';
    }

    return 'Ce champs contient une erreur';
  }
}
