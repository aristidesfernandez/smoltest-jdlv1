import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IOperator, NewOperator } from '../operator.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IOperator for edit and NewOperatorFormGroupInput for create.
 */
type OperatorFormGroupInput = IOperator | PartialWithRequiredKeyOf<NewOperator>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IOperator | NewOperator> = Omit<T, 'endDate' | 'startDate'> & {
  endDate?: string | null;
  startDate?: string | null;
};

type OperatorFormRawValue = FormValueOf<IOperator>;

type NewOperatorFormRawValue = FormValueOf<NewOperator>;

type OperatorFormDefaults = Pick<NewOperator, 'id' | 'endDate' | 'startDate'>;

type OperatorFormGroupContent = {
  id: FormControl<OperatorFormRawValue['id'] | NewOperator['id']>;
  permitDescription: FormControl<OperatorFormRawValue['permitDescription']>;
  endDate: FormControl<OperatorFormRawValue['endDate']>;
  startDate: FormControl<OperatorFormRawValue['startDate']>;
  minAccumulatedPrize: FormControl<OperatorFormRawValue['minAccumulatedPrize']>;
  minIndividualPrize: FormControl<OperatorFormRawValue['minIndividualPrize']>;
  minTransactionAccumulated: FormControl<OperatorFormRawValue['minTransactionAccumulated']>;
  minIndividualTransaction: FormControl<OperatorFormRawValue['minIndividualTransaction']>;
  nit: FormControl<OperatorFormRawValue['nit']>;
  contractNumber: FormControl<OperatorFormRawValue['contractNumber']>;
  eventQuantity: FormControl<OperatorFormRawValue['eventQuantity']>;
  companyName: FormControl<OperatorFormRawValue['companyName']>;
  brand: FormControl<OperatorFormRawValue['brand']>;
  municipality: FormControl<OperatorFormRawValue['municipality']>;
};

export type OperatorFormGroup = FormGroup<OperatorFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class OperatorFormService {
  createOperatorFormGroup(operator: OperatorFormGroupInput = { id: null }): OperatorFormGroup {
    const operatorRawValue = this.convertOperatorToOperatorRawValue({
      ...this.getFormDefaults(),
      ...operator,
    });
    return new FormGroup<OperatorFormGroupContent>({
      id: new FormControl(
        { value: operatorRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      permitDescription: new FormControl(operatorRawValue.permitDescription, {
        validators: [Validators.maxLength(50)],
      }),
      endDate: new FormControl(operatorRawValue.endDate),
      startDate: new FormControl(operatorRawValue.startDate),
      minAccumulatedPrize: new FormControl(operatorRawValue.minAccumulatedPrize),
      minIndividualPrize: new FormControl(operatorRawValue.minIndividualPrize),
      minTransactionAccumulated: new FormControl(operatorRawValue.minTransactionAccumulated),
      minIndividualTransaction: new FormControl(operatorRawValue.minIndividualTransaction),
      nit: new FormControl(operatorRawValue.nit, {
        validators: [Validators.maxLength(50)],
      }),
      contractNumber: new FormControl(operatorRawValue.contractNumber, {
        validators: [Validators.maxLength(50)],
      }),
      eventQuantity: new FormControl(operatorRawValue.eventQuantity),
      companyName: new FormControl(operatorRawValue.companyName),
      brand: new FormControl(operatorRawValue.brand, {
        validators: [Validators.maxLength(50)],
      }),
      municipality: new FormControl(operatorRawValue.municipality),
    });
  }

  getOperator(form: OperatorFormGroup): IOperator | NewOperator {
    return this.convertOperatorRawValueToOperator(form.getRawValue() as OperatorFormRawValue | NewOperatorFormRawValue);
  }

  resetForm(form: OperatorFormGroup, operator: OperatorFormGroupInput): void {
    const operatorRawValue = this.convertOperatorToOperatorRawValue({ ...this.getFormDefaults(), ...operator });
    form.reset(
      {
        ...operatorRawValue,
        id: { value: operatorRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): OperatorFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      endDate: currentTime,
      startDate: currentTime,
    };
  }

  private convertOperatorRawValueToOperator(rawOperator: OperatorFormRawValue | NewOperatorFormRawValue): IOperator | NewOperator {
    return {
      ...rawOperator,
      endDate: dayjs(rawOperator.endDate, DATE_TIME_FORMAT),
      startDate: dayjs(rawOperator.startDate, DATE_TIME_FORMAT),
    };
  }

  private convertOperatorToOperatorRawValue(
    operator: IOperator | (Partial<NewOperator> & OperatorFormDefaults)
  ): OperatorFormRawValue | PartialWithRequiredKeyOf<NewOperatorFormRawValue> {
    return {
      ...operator,
      endDate: operator.endDate ? operator.endDate.format(DATE_TIME_FORMAT) : undefined,
      startDate: operator.startDate ? operator.startDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
