import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { InterfacingFormService, InterfacingFormGroup } from './interfacing-form.service';
import { IInterfacing } from '../interfacing.model';
import { InterfacingService } from '../service/interfacing.service';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { EstablishmentService } from 'app/entities/establishment/service/establishment.service';

@Component({
  selector: 'jhi-interfacing-update',
  templateUrl: './interfacing-update.component.html',
})
export class InterfacingUpdateComponent implements OnInit {
  isSaving = false;
  interfacing: IInterfacing | null = null;

  establishmentsSharedCollection: IEstablishment[] = [];

  editForm: InterfacingFormGroup = this.interfacingFormService.createInterfacingFormGroup();

  constructor(
    protected interfacingService: InterfacingService,
    protected interfacingFormService: InterfacingFormService,
    protected establishmentService: EstablishmentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEstablishment = (o1: IEstablishment | null, o2: IEstablishment | null): boolean =>
    this.establishmentService.compareEstablishment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ interfacing }) => {
      this.interfacing = interfacing;
      if (interfacing) {
        this.updateForm(interfacing);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const interfacing = this.interfacingFormService.getInterfacing(this.editForm);
    if (interfacing.id !== null) {
      this.subscribeToSaveResponse(this.interfacingService.update(interfacing));
    } else {
      this.subscribeToSaveResponse(this.interfacingService.create(interfacing));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInterfacing>>): void {
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

  protected updateForm(interfacing: IInterfacing): void {
    this.interfacing = interfacing;
    this.interfacingFormService.resetForm(this.editForm, interfacing);

    this.establishmentsSharedCollection = this.establishmentService.addEstablishmentToCollectionIfMissing<IEstablishment>(
      this.establishmentsSharedCollection,
      interfacing.idEstablishment
    );
  }

  protected loadRelationshipsOptions(): void {
    this.establishmentService
      .query()
      .pipe(map((res: HttpResponse<IEstablishment[]>) => res.body ?? []))
      .pipe(
        map((establishments: IEstablishment[]) =>
          this.establishmentService.addEstablishmentToCollectionIfMissing<IEstablishment>(establishments, this.interfacing?.idEstablishment)
        )
      )
      .subscribe((establishments: IEstablishment[]) => (this.establishmentsSharedCollection = establishments));
  }
}
