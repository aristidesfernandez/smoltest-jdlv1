import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import {
  OperationalPropertiesEstablishmentFormService,
  OperationalPropertiesEstablishmentFormGroup,
} from './operational-properties-establishment-form.service';
import { IOperationalPropertiesEstablishment } from '../operational-properties-establishment.model';
import { OperationalPropertiesEstablishmentService } from '../service/operational-properties-establishment.service';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { EstablishmentService } from 'app/entities/establishment/service/establishment.service';

@Component({
  selector: 'jhi-operational-properties-establishment-update',
  templateUrl: './operational-properties-establishment-update.component.html',
})
export class OperationalPropertiesEstablishmentUpdateComponent implements OnInit {
  isSaving = false;
  operationalPropertiesEstablishment: IOperationalPropertiesEstablishment | null = null;

  establishmentsSharedCollection: IEstablishment[] = [];

  editForm: OperationalPropertiesEstablishmentFormGroup =
    this.operationalPropertiesEstablishmentFormService.createOperationalPropertiesEstablishmentFormGroup();

  constructor(
    protected operationalPropertiesEstablishmentService: OperationalPropertiesEstablishmentService,
    protected operationalPropertiesEstablishmentFormService: OperationalPropertiesEstablishmentFormService,
    protected establishmentService: EstablishmentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEstablishment = (o1: IEstablishment | null, o2: IEstablishment | null): boolean =>
    this.establishmentService.compareEstablishment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operationalPropertiesEstablishment }) => {
      this.operationalPropertiesEstablishment = operationalPropertiesEstablishment;
      if (operationalPropertiesEstablishment) {
        this.updateForm(operationalPropertiesEstablishment);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const operationalPropertiesEstablishment = this.operationalPropertiesEstablishmentFormService.getOperationalPropertiesEstablishment(
      this.editForm
    );
    if (operationalPropertiesEstablishment.id !== null) {
      this.subscribeToSaveResponse(this.operationalPropertiesEstablishmentService.update(operationalPropertiesEstablishment));
    } else {
      this.subscribeToSaveResponse(this.operationalPropertiesEstablishmentService.create(operationalPropertiesEstablishment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperationalPropertiesEstablishment>>): void {
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

  protected updateForm(operationalPropertiesEstablishment: IOperationalPropertiesEstablishment): void {
    this.operationalPropertiesEstablishment = operationalPropertiesEstablishment;
    this.operationalPropertiesEstablishmentFormService.resetForm(this.editForm, operationalPropertiesEstablishment);

    this.establishmentsSharedCollection = this.establishmentService.addEstablishmentToCollectionIfMissing<IEstablishment>(
      this.establishmentsSharedCollection,
      operationalPropertiesEstablishment.idEstablishment
    );
  }

  protected loadRelationshipsOptions(): void {
    this.establishmentService
      .query()
      .pipe(map((res: HttpResponse<IEstablishment[]>) => res.body ?? []))
      .pipe(
        map((establishments: IEstablishment[]) =>
          this.establishmentService.addEstablishmentToCollectionIfMissing<IEstablishment>(
            establishments,
            this.operationalPropertiesEstablishment?.idEstablishment
          )
        )
      )
      .subscribe((establishments: IEstablishment[]) => (this.establishmentsSharedCollection = establishments));
  }
}
