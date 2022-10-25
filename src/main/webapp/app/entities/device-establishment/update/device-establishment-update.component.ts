import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DeviceEstablishmentFormService, DeviceEstablishmentFormGroup } from './device-establishment-form.service';
import { IDeviceEstablishment } from '../device-establishment.model';
import { DeviceEstablishmentService } from '../service/device-establishment.service';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { EstablishmentService } from 'app/entities/establishment/service/establishment.service';

@Component({
  selector: 'jhi-device-establishment-update',
  templateUrl: './device-establishment-update.component.html',
})
export class DeviceEstablishmentUpdateComponent implements OnInit {
  isSaving = false;
  deviceEstablishment: IDeviceEstablishment | null = null;

  establishmentsSharedCollection: IEstablishment[] = [];

  editForm: DeviceEstablishmentFormGroup = this.deviceEstablishmentFormService.createDeviceEstablishmentFormGroup();

  constructor(
    protected deviceEstablishmentService: DeviceEstablishmentService,
    protected deviceEstablishmentFormService: DeviceEstablishmentFormService,
    protected establishmentService: EstablishmentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareEstablishment = (o1: IEstablishment | null, o2: IEstablishment | null): boolean =>
    this.establishmentService.compareEstablishment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deviceEstablishment }) => {
      this.deviceEstablishment = deviceEstablishment;
      if (deviceEstablishment) {
        this.updateForm(deviceEstablishment);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deviceEstablishment = this.deviceEstablishmentFormService.getDeviceEstablishment(this.editForm);
    if (deviceEstablishment.id !== null) {
      this.subscribeToSaveResponse(this.deviceEstablishmentService.update(deviceEstablishment));
    } else {
      this.subscribeToSaveResponse(this.deviceEstablishmentService.create(deviceEstablishment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeviceEstablishment>>): void {
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

  protected updateForm(deviceEstablishment: IDeviceEstablishment): void {
    this.deviceEstablishment = deviceEstablishment;
    this.deviceEstablishmentFormService.resetForm(this.editForm, deviceEstablishment);

    this.establishmentsSharedCollection = this.establishmentService.addEstablishmentToCollectionIfMissing<IEstablishment>(
      this.establishmentsSharedCollection,
      deviceEstablishment.idEstablishment
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
            this.deviceEstablishment?.idEstablishment
          )
        )
      )
      .subscribe((establishments: IEstablishment[]) => (this.establishmentsSharedCollection = establishments));
  }
}
