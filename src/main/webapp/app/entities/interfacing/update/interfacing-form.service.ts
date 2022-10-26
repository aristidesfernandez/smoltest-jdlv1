import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IInterfacing, NewInterfacing } from '../interfacing.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IInterfacing for edit and NewInterfacingFormGroupInput for create.
 */
type InterfacingFormGroupInput = IInterfacing | PartialWithRequiredKeyOf<NewInterfacing>;

type InterfacingFormDefaults = Pick<NewInterfacing, 'id' | 'isAssigned'>;

type InterfacingFormGroupContent = {
  id: FormControl<IInterfacing['id'] | NewInterfacing['id']>;
  isAssigned: FormControl<IInterfacing['isAssigned']>;
  ipAddress: FormControl<IInterfacing['ipAddress']>;
  hash: FormControl<IInterfacing['hash']>;
  serial: FormControl<IInterfacing['serial']>;
  version: FormControl<IInterfacing['version']>;
  port: FormControl<IInterfacing['port']>;
  establishment: FormControl<IInterfacing['establishment']>;
};

export type InterfacingFormGroup = FormGroup<InterfacingFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class InterfacingFormService {
  createInterfacingFormGroup(interfacing: InterfacingFormGroupInput = { id: null }): InterfacingFormGroup {
    const interfacingRawValue = {
      ...this.getFormDefaults(),
      ...interfacing,
    };
    return new FormGroup<InterfacingFormGroupContent>({
      id: new FormControl(
        { value: interfacingRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      isAssigned: new FormControl(interfacingRawValue.isAssigned),
      ipAddress: new FormControl(interfacingRawValue.ipAddress, {
        validators: [Validators.maxLength(25)],
      }),
      hash: new FormControl(interfacingRawValue.hash),
      serial: new FormControl(interfacingRawValue.serial, {
        validators: [Validators.maxLength(50)],
      }),
      version: new FormControl(interfacingRawValue.version, {
        validators: [Validators.maxLength(10)],
      }),
      port: new FormControl(interfacingRawValue.port),
      establishment: new FormControl(interfacingRawValue.establishment, {
        validators: [Validators.required],
      }),
    });
  }

  getInterfacing(form: InterfacingFormGroup): IInterfacing | NewInterfacing {
    return form.getRawValue() as IInterfacing | NewInterfacing;
  }

  resetForm(form: InterfacingFormGroup, interfacing: InterfacingFormGroupInput): void {
    const interfacingRawValue = { ...this.getFormDefaults(), ...interfacing };
    form.reset(
      {
        ...interfacingRawValue,
        id: { value: interfacingRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): InterfacingFormDefaults {
    return {
      id: null,
      isAssigned: false,
    };
  }
}
