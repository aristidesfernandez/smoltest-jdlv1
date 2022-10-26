import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IEstablishment, NewEstablishment } from '../establishment.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IEstablishment for edit and NewEstablishmentFormGroupInput for create.
 */
type EstablishmentFormGroupInput = IEstablishment | PartialWithRequiredKeyOf<NewEstablishment>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IEstablishment | NewEstablishment> = Omit<T, 'liquidationTime' | 'closeTime' | 'startTime'> & {
  liquidationTime?: string | null;
  closeTime?: string | null;
  startTime?: string | null;
};

type EstablishmentFormRawValue = FormValueOf<IEstablishment>;

type NewEstablishmentFormRawValue = FormValueOf<NewEstablishment>;

type EstablishmentFormDefaults = Pick<NewEstablishment, 'id' | 'liquidationTime' | 'closeTime' | 'startTime'>;

type EstablishmentFormGroupContent = {
  id: FormControl<EstablishmentFormRawValue['id'] | NewEstablishment['id']>;
  liquidationTime: FormControl<EstablishmentFormRawValue['liquidationTime']>;
  name: FormControl<EstablishmentFormRawValue['name']>;
  type: FormControl<EstablishmentFormRawValue['type']>;
  municipalityCode: FormControl<EstablishmentFormRawValue['municipalityCode']>;
  neighborhood: FormControl<EstablishmentFormRawValue['neighborhood']>;
  address: FormControl<EstablishmentFormRawValue['address']>;
  coljuegosCode: FormControl<EstablishmentFormRawValue['coljuegosCode']>;
  closeTime: FormControl<EstablishmentFormRawValue['closeTime']>;
  startTime: FormControl<EstablishmentFormRawValue['startTime']>;
  activityType: FormControl<EstablishmentFormRawValue['activityType']>;
  longitude: FormControl<EstablishmentFormRawValue['longitude']>;
  latitude: FormControl<EstablishmentFormRawValue['latitude']>;
  mercantileRegistration: FormControl<EstablishmentFormRawValue['mercantileRegistration']>;
  idOperator: FormControl<EstablishmentFormRawValue['idOperator']>;
};

export type EstablishmentFormGroup = FormGroup<EstablishmentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class EstablishmentFormService {
  createEstablishmentFormGroup(establishment: EstablishmentFormGroupInput = { id: null }): EstablishmentFormGroup {
    const establishmentRawValue = this.convertEstablishmentToEstablishmentRawValue({
      ...this.getFormDefaults(),
      ...establishment,
    });
    return new FormGroup<EstablishmentFormGroupContent>({
      id: new FormControl(
        { value: establishmentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      liquidationTime: new FormControl(establishmentRawValue.liquidationTime),
      name: new FormControl(establishmentRawValue.name),
      type: new FormControl(establishmentRawValue.type),
      municipalityCode: new FormControl(establishmentRawValue.municipalityCode),
      neighborhood: new FormControl(establishmentRawValue.neighborhood),
      address: new FormControl(establishmentRawValue.address),
      coljuegosCode: new FormControl(establishmentRawValue.coljuegosCode),
      closeTime: new FormControl(establishmentRawValue.closeTime),
      startTime: new FormControl(establishmentRawValue.startTime),
      activityType: new FormControl(establishmentRawValue.activityType),
      longitude: new FormControl(establishmentRawValue.longitude),
      latitude: new FormControl(establishmentRawValue.latitude),
      mercantileRegistration: new FormControl(establishmentRawValue.mercantileRegistration),
      idOperator: new FormControl(establishmentRawValue.idOperator),
    });
  }

  getEstablishment(form: EstablishmentFormGroup): IEstablishment | NewEstablishment {
    return this.convertEstablishmentRawValueToEstablishment(form.getRawValue() as EstablishmentFormRawValue | NewEstablishmentFormRawValue);
  }

  resetForm(form: EstablishmentFormGroup, establishment: EstablishmentFormGroupInput): void {
    const establishmentRawValue = this.convertEstablishmentToEstablishmentRawValue({ ...this.getFormDefaults(), ...establishment });
    form.reset(
      {
        ...establishmentRawValue,
        id: { value: establishmentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): EstablishmentFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      liquidationTime: currentTime,
      closeTime: currentTime,
      startTime: currentTime,
    };
  }

  private convertEstablishmentRawValueToEstablishment(
    rawEstablishment: EstablishmentFormRawValue | NewEstablishmentFormRawValue
  ): IEstablishment | NewEstablishment {
    return {
      ...rawEstablishment,
      liquidationTime: dayjs(rawEstablishment.liquidationTime, DATE_TIME_FORMAT),
      closeTime: dayjs(rawEstablishment.closeTime, DATE_TIME_FORMAT),
      startTime: dayjs(rawEstablishment.startTime, DATE_TIME_FORMAT),
    };
  }

  private convertEstablishmentToEstablishmentRawValue(
    establishment: IEstablishment | (Partial<NewEstablishment> & EstablishmentFormDefaults)
  ): EstablishmentFormRawValue | PartialWithRequiredKeyOf<NewEstablishmentFormRawValue> {
    return {
      ...establishment,
      liquidationTime: establishment.liquidationTime ? establishment.liquidationTime.format(DATE_TIME_FORMAT) : undefined,
      closeTime: establishment.closeTime ? establishment.closeTime.format(DATE_TIME_FORMAT) : undefined,
      startTime: establishment.startTime ? establishment.startTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
