import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { passwordValidator } from '../../validators/password.validator';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private formbuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formbuilder.group({
      username: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [
        null,
        [Validators.required, Validators.minLength(8), passwordValidator()],
      ],
    });
  }

  onRegister() {
    console.log(this.registerForm.value);
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
