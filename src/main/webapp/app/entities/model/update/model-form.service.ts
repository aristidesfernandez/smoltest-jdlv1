import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IModel, NewModel } from '../model.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IModel for edit and NewModelFormGroupInput for create.
 */
type ModelFormGroupInput = IModel | PartialWithRequiredKeyOf<NewModel>;

type ModelFormDefaults = Pick<NewModel, 'id' | 'subtractBonus' | 'idModels'>;

type ModelFormGroupContent = {
  id: FormControl<IModel['id'] | NewModel['id']>;
  code: FormControl<IModel['code']>;
  name: FormControl<IModel['name']>;
  subtractBonus: FormControl<IModel['subtractBonus']>;
  collectionCeil: FormControl<IModel['collectionCeil']>;
  rolloverLimit: FormControl<IModel['rolloverLimit']>;
  idManufacturer: FormControl<IModel['idManufacturer']>;
  idFormula: FormControl<IModel['idFormula']>;
  idModels: FormControl<IModel['idModels']>;
};

export type ModelFormGroup = FormGroup<ModelFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ModelFormService {
  createModelFormGroup(model: ModelFormGroupInput = { id: null }): ModelFormGroup {
    const modelRawValue = {
      ...this.getFormDefaults(),
      ...model,
    };
    return new FormGroup<ModelFormGroupContent>({
      id: new FormControl(
        { value: modelRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      code: new FormControl(modelRawValue.code, {
        validators: [Validators.required],
      }),
      name: new FormControl(modelRawValue.name),
      subtractBonus: new FormControl(modelRawValue.subtractBonus),
      collectionCeil: new FormControl(modelRawValue.collectionCeil),
      rolloverLimit: new FormControl(modelRawValue.rolloverLimit),
      idManufacturer: new FormControl(modelRawValue.idManufacturer, {
        validators: [Validators.required],
      }),
      idFormula: new FormControl(modelRawValue.idFormula, {
        validators: [Validators.required],
      }),
      idModels: new FormControl(modelRawValue.idModels ?? []),
    });
  }

  getModel(form: ModelFormGroup): IModel | NewModel {
    return form.getRawValue() as IModel | NewModel;
  }

  resetForm(form: ModelFormGroup, model: ModelFormGroupInput): void {
    const modelRawValue = { ...this.getFormDefaults(), ...model };
    form.reset(
      {
        ...modelRawValue,
        id: { value: modelRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ModelFormDefaults {
    return {
      id: null,
      subtractBonus: false,
      idModels: [],
    };
  }
}
