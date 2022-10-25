import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICounterType, NewCounterType } from '../counter-type.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICounterType for edit and NewCounterTypeFormGroupInput for create.
 */
type CounterTypeFormGroupInput = ICounterType | PartialWithRequiredKeyOf<NewCounterType>;

type CounterTypeFormDefaults = Pick<NewCounterType, 'id' | 'includedInFormula' | 'prize'>;

type CounterTypeFormGroupContent = {
  id: FormControl<ICounterType['id'] | NewCounterType['id']>;
  counterCode: FormControl<ICounterType['counterCode']>;
  name: FormControl<ICounterType['name']>;
  description: FormControl<ICounterType['description']>;
  includedInFormula: FormControl<ICounterType['includedInFormula']>;
  prize: FormControl<ICounterType['prize']>;
  category: FormControl<ICounterType['category']>;
  udteWaitTime: FormControl<ICounterType['udteWaitTime']>;
};

export type CounterTypeFormGroup = FormGroup<CounterTypeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CounterTypeFormService {
  createCounterTypeFormGroup(counterType: CounterTypeFormGroupInput = { id: null }): CounterTypeFormGroup {
    const counterTypeRawValue = {
      ...this.getFormDefaults(),
      ...counterType,
    };
    return new FormGroup<CounterTypeFormGroupContent>({
      id: new FormControl(
        { value: counterTypeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      counterCode: new FormControl(counterTypeRawValue.counterCode, {
        validators: [Validators.required],
      }),
      name: new FormControl(counterTypeRawValue.name),
      description: new FormControl(counterTypeRawValue.description),
      includedInFormula: new FormControl(counterTypeRawValue.includedInFormula),
      prize: new FormControl(counterTypeRawValue.prize),
      category: new FormControl(counterTypeRawValue.category),
      udteWaitTime: new FormControl(counterTypeRawValue.udteWaitTime),
    });
  }

  getCounterType(form: CounterTypeFormGroup): ICounterType | NewCounterType {
    return form.getRawValue() as ICounterType | NewCounterType;
  }

  resetForm(form: CounterTypeFormGroup, counterType: CounterTypeFormGroupInput): void {
    const counterTypeRawValue = { ...this.getFormDefaults(), ...counterType };
    form.reset(
      {
        ...counterTypeRawValue,
        id: { value: counterTypeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CounterTypeFormDefaults {
    return {
      id: null,
      includedInFormula: false,
      prize: false,
    };
  }
}
