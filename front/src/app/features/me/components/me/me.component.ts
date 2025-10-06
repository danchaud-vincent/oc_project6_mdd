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
import { Observable, tap } from 'rxjs';
import { Me } from '../../models/me.model';
import { MeService } from '../../services/me.service';
import { MeUpdateRequest } from '../../models/meUpdateRequest.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TopicListComponent } from '../../../topics/components/topic-list/topic-list.component';

@Component({
  selector: 'app-me',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    ButtonComponent,
    TopicListComponent,
  ],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss',
})
export class MeComponent implements OnInit {
  @ViewChild(FormGroupDirective) formDirective!: FormGroupDirective;

  meForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private meService: MeService,
    private matSnackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
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
