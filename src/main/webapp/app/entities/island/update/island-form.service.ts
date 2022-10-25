import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IIsland, NewIsland } from '../island.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IIsland for edit and NewIslandFormGroupInput for create.
 */
type IslandFormGroupInput = IIsland | PartialWithRequiredKeyOf<NewIsland>;

type IslandFormDefaults = Pick<NewIsland, 'id'>;

type IslandFormGroupContent = {
  id: FormControl<IIsland['id'] | NewIsland['id']>;
  description: FormControl<IIsland['description']>;
  name: FormControl<IIsland['name']>;
};

export type IslandFormGroup = FormGroup<IslandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class IslandFormService {
  createIslandFormGroup(island: IslandFormGroupInput = { id: null }): IslandFormGroup {
    const islandRawValue = {
      ...this.getFormDefaults(),
      ...island,
    };
    return new FormGroup<IslandFormGroupContent>({
      id: new FormControl(
        { value: islandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      description: new FormControl(islandRawValue.description),
      name: new FormControl(islandRawValue.name),
    });
  }

  getIsland(form: IslandFormGroup): IIsland | NewIsland {
    return form.getRawValue() as IIsland | NewIsland;
  }

  resetForm(form: IslandFormGroup, island: IslandFormGroupInput): void {
    const islandRawValue = { ...this.getFormDefaults(), ...island };
    form.reset(
      {
        ...islandRawValue,
        id: { value: islandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): IslandFormDefaults {
    return {
      id: null,
    };
  }
}
