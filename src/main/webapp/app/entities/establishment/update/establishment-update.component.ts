import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { EstablishmentFormService, EstablishmentFormGroup } from './establishment-form.service';
import { IEstablishment } from '../establishment.model';
import { EstablishmentService } from '../service/establishment.service';
import { IOperator } from 'app/entities/operator/operator.model';
import { OperatorService } from 'app/entities/operator/service/operator.service';
import { EstablishmentType } from 'app/entities/enumerations/establishment-type.model';

@Component({
  selector: 'jhi-establishment-update',
  templateUrl: './establishment-update.component.html',
})
export class EstablishmentUpdateComponent implements OnInit {
  isSaving = false;
  establishment: IEstablishment | null = null;
  establishmentTypeValues = Object.keys(EstablishmentType);

  operatorsSharedCollection: IOperator[] = [];

  editForm: EstablishmentFormGroup = this.establishmentFormService.createEstablishmentFormGroup();

  constructor(
    protected establishmentService: EstablishmentService,
    protected establishmentFormService: EstablishmentFormService,
    protected operatorService: OperatorService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareOperator = (o1: IOperator | null, o2: IOperator | null): boolean => this.operatorService.compareOperator(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ establishment }) => {
      this.establishment = establishment;
      if (establishment) {
        this.updateForm(establishment);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const establishment = this.establishmentFormService.getEstablishment(this.editForm);
    if (establishment.id !== null) {
      this.subscribeToSaveResponse(this.establishmentService.update(establishment));
    } else {
      this.subscribeToSaveResponse(this.establishmentService.create(establishment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstablishment>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(establishment: IEstablishment): void {
    this.establishment = establishment;
    this.establishmentFormService.resetForm(this.editForm, establishment);

    this.operatorsSharedCollection = this.operatorService.addOperatorToCollectionIfMissing<IOperator>(
      this.operatorsSharedCollection,
      establishment.idOperator
    );
  }

  protected loadRelationshipsOptions(): void {
    this.operatorService
      .query()
      .pipe(map((res: HttpResponse<IOperator[]>) => res.body ?? []))
      .pipe(
        map((operators: IOperator[]) =>
          this.operatorService.addOperatorToCollectionIfMissing<IOperator>(operators, this.establishment?.idOperator)
        )
      )
      .subscribe((operators: IOperator[]) => (this.operatorsSharedCollection = operators));
  }
}
