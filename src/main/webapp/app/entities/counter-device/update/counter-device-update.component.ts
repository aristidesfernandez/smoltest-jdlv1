import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { CounterDeviceFormService, CounterDeviceFormGroup } from './counter-device-form.service';
import { ICounterDevice } from '../counter-device.model';
import { CounterDeviceService } from '../service/counter-device.service';
import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { CounterTypeService } from 'app/entities/counter-type/service/counter-type.service';
import { IDevice } from 'app/entities/device/device.model';
import { DeviceService } from 'app/entities/device/service/device.service';

@Component({
  selector: 'jhi-counter-device-update',
  templateUrl: './counter-device-update.component.html',
})
export class CounterDeviceUpdateComponent implements OnInit {
  isSaving = false;
  counterDevice: ICounterDevice | null = null;

  counterTypesSharedCollection: ICounterType[] = [];
  devicesSharedCollection: IDevice[] = [];

  editForm: CounterDeviceFormGroup = this.counterDeviceFormService.createCounterDeviceFormGroup();

  constructor(
    protected counterDeviceService: CounterDeviceService,
    protected counterDeviceFormService: CounterDeviceFormService,
    protected counterTypeService: CounterTypeService,
    protected deviceService: DeviceService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCounterType = (o1: ICounterType | null, o2: ICounterType | null): boolean => this.counterTypeService.compareCounterType(o1, o2);

  compareDevice = (o1: IDevice | null, o2: IDevice | null): boolean => this.deviceService.compareDevice(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ counterDevice }) => {
      this.counterDevice = counterDevice;
      if (counterDevice) {
        this.updateForm(counterDevice);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const counterDevice = this.counterDeviceFormService.getCounterDevice(this.editForm);
    if (counterDevice.id !== null) {
      this.subscribeToSaveResponse(this.counterDeviceService.update(counterDevice));
    } else {
      this.subscribeToSaveResponse(this.counterDeviceService.create(counterDevice));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICounterDevice>>): void {
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

  protected updateForm(counterDevice: ICounterDevice): void {
    this.counterDevice = counterDevice;
    this.counterDeviceFormService.resetForm(this.editForm, counterDevice);

    this.counterTypesSharedCollection = this.counterTypeService.addCounterTypeToCollectionIfMissing<ICounterType>(
      this.counterTypesSharedCollection,
      counterDevice.counterCode
    );
    this.devicesSharedCollection = this.deviceService.addDeviceToCollectionIfMissing<IDevice>(
      this.devicesSharedCollection,
      counterDevice.idDevice
    );
  }

  protected loadRelationshipsOptions(): void {
    this.counterTypeService
      .query()
      .pipe(map((res: HttpResponse<ICounterType[]>) => res.body ?? []))
      .pipe(
        map((counterTypes: ICounterType[]) =>
          this.counterTypeService.addCounterTypeToCollectionIfMissing<ICounterType>(counterTypes, this.counterDevice?.counterCode)
        )
      )
      .subscribe((counterTypes: ICounterType[]) => (this.counterTypesSharedCollection = counterTypes));

    this.deviceService
      .query()
      .pipe(map((res: HttpResponse<IDevice[]>) => res.body ?? []))
      .pipe(map((devices: IDevice[]) => this.deviceService.addDeviceToCollectionIfMissing<IDevice>(devices, this.counterDevice?.idDevice)))
      .subscribe((devices: IDevice[]) => (this.devicesSharedCollection = devices));
  }
}
