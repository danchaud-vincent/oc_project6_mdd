import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordValidator(): ValidatorFn {
  return (ctrl: AbstractControl): null | ValidationErrors => {
    const value: string = ctrl.value || '';
    const errors: ValidationErrors = {};

    if (value.length < 8) {
      errors['minlength'] = { requiredLength: 8, actualLength: value.length };
    }

    if (!/[A-Z]/.test(value)) {
      errors['missingUpperCase'] = true;
    }

    if (!/[a-z]/.test(value)) {
      errors['missingLowerCase'] = true;
    }

    if (!/\d/.test(value)) {
      errors['missingNumber'] = true;
    }
    if (!/[\W_]/.test(value)) {
      errors['missingSpecialChar'] = true;
    }

    return Object.keys(errors).length ? errors : null;
  };
}
