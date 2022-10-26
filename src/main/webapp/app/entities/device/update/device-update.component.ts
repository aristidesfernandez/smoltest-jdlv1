import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DeviceFormService, DeviceFormGroup } from './device-form.service';
import { IDevice } from '../device.model';
import { DeviceService } from '../service/device.service';
import { IInterfacing } from 'app/entities/interfacing/interfacing.model';
import { InterfacingService } from 'app/entities/interfacing/service/interfacing.service';
import { IModel } from 'app/entities/model/model.model';
import { ModelService } from 'app/entities/model/service/model.service';
import { IDeviceCategory } from 'app/entities/device-category/device-category.model';
import { DeviceCategoryService } from 'app/entities/device-category/service/device-category.service';
import { IDeviceType } from 'app/entities/device-type/device-type.model';
import { DeviceTypeService } from 'app/entities/device-type/service/device-type.service';
import { IFormula } from 'app/entities/formula/formula.model';
import { FormulaService } from 'app/entities/formula/service/formula.service';
import { IIsland } from 'app/entities/island/island.model';
import { IslandService } from 'app/entities/island/service/island.service';
import { ICurrencyType } from 'app/entities/currency-type/currency-type.model';
import { CurrencyTypeService } from 'app/entities/currency-type/service/currency-type.service';

@Component({
  selector: 'jhi-device-update',
  templateUrl: './device-update.component.html',
})
export class DeviceUpdateComponent implements OnInit {
  isSaving = false;
  device: IDevice | null = null;

  interfacingsSharedCollection: IInterfacing[] = [];
  modelsSharedCollection: IModel[] = [];
  deviceCategoriesSharedCollection: IDeviceCategory[] = [];
  deviceTypesSharedCollection: IDeviceType[] = [];
  formulasSharedCollection: IFormula[] = [];
  islandsSharedCollection: IIsland[] = [];
  currencyTypesSharedCollection: ICurrencyType[] = [];

  editForm: DeviceFormGroup = this.deviceFormService.createDeviceFormGroup();

  constructor(
    protected deviceService: DeviceService,
    protected deviceFormService: DeviceFormService,
    protected interfacingService: InterfacingService,
    protected modelService: ModelService,
    protected deviceCategoryService: DeviceCategoryService,
    protected deviceTypeService: DeviceTypeService,
    protected formulaService: FormulaService,
    protected islandService: IslandService,
    protected currencyTypeService: CurrencyTypeService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareInterfacing = (o1: IInterfacing | null, o2: IInterfacing | null): boolean => this.interfacingService.compareInterfacing(o1, o2);

  compareModel = (o1: IModel | null, o2: IModel | null): boolean => this.modelService.compareModel(o1, o2);

  compareDeviceCategory = (o1: IDeviceCategory | null, o2: IDeviceCategory | null): boolean =>
    this.deviceCategoryService.compareDeviceCategory(o1, o2);

  compareDeviceType = (o1: IDeviceType | null, o2: IDeviceType | null): boolean => this.deviceTypeService.compareDeviceType(o1, o2);

  compareFormula = (o1: IFormula | null, o2: IFormula | null): boolean => this.formulaService.compareFormula(o1, o2);

  compareIsland = (o1: IIsland | null, o2: IIsland | null): boolean => this.islandService.compareIsland(o1, o2);

  compareCurrencyType = (o1: ICurrencyType | null, o2: ICurrencyType | null): boolean =>
    this.currencyTypeService.compareCurrencyType(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ device }) => {
      this.device = device;
      if (device) {
        this.updateForm(device);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const device = this.deviceFormService.getDevice(this.editForm);
    if (device.id !== null) {
      this.subscribeToSaveResponse(this.deviceService.update(device));
    } else {
      this.subscribeToSaveResponse(this.deviceService.create(device));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDevice>>): void {
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

  protected updateForm(device: IDevice): void {
    this.device = device;
    this.deviceFormService.resetForm(this.editForm, device);

    this.interfacingsSharedCollection = this.interfacingService.addInterfacingToCollectionIfMissing<IInterfacing>(
      this.interfacingsSharedCollection,
      device.idInterfacing
    );
    this.modelsSharedCollection = this.modelService.addModelToCollectionIfMissing<IModel>(this.modelsSharedCollection, device.idModel);
    this.deviceCategoriesSharedCollection = this.deviceCategoryService.addDeviceCategoryToCollectionIfMissing<IDeviceCategory>(
      this.deviceCategoriesSharedCollection,
      device.idDeviceCategory
    );
    this.deviceTypesSharedCollection = this.deviceTypeService.addDeviceTypeToCollectionIfMissing<IDeviceType>(
      this.deviceTypesSharedCollection,
      device.idDeviceType
    );
    this.formulasSharedCollection = this.formulaService.addFormulaToCollectionIfMissing<IFormula>(
      this.formulasSharedCollection,
      device.idFormulaHandpay,
      device.idFormulaJackpot
    );
    this.islandsSharedCollection = this.islandService.addIslandToCollectionIfMissing<IIsland>(
      this.islandsSharedCollection,
      device.idIsland
    );
    this.currencyTypesSharedCollection = this.currencyTypeService.addCurrencyTypeToCollectionIfMissing<ICurrencyType>(
      this.currencyTypesSharedCollection,
      device.idCurrencyType
    );
  }

  protected loadRelationshipsOptions(): void {
    this.interfacingService
      .query()
      .pipe(map((res: HttpResponse<IInterfacing[]>) => res.body ?? []))
      .pipe(
        map((interfacings: IInterfacing[]) =>
          this.interfacingService.addInterfacingToCollectionIfMissing<IInterfacing>(interfacings, this.device?.idInterfacing)
        )
      )
      .subscribe((interfacings: IInterfacing[]) => (this.interfacingsSharedCollection = interfacings));

    this.modelService
      .query()
      .pipe(map((res: HttpResponse<IModel[]>) => res.body ?? []))
      .pipe(map((models: IModel[]) => this.modelService.addModelToCollectionIfMissing<IModel>(models, this.device?.idModel)))
      .subscribe((models: IModel[]) => (this.modelsSharedCollection = models));

    this.deviceCategoryService
      .query()
      .pipe(map((res: HttpResponse<IDeviceCategory[]>) => res.body ?? []))
      .pipe(
        map((deviceCategories: IDeviceCategory[]) =>
          this.deviceCategoryService.addDeviceCategoryToCollectionIfMissing<IDeviceCategory>(
            deviceCategories,
            this.device?.idDeviceCategory
          )
        )
      )
      .subscribe((deviceCategories: IDeviceCategory[]) => (this.deviceCategoriesSharedCollection = deviceCategories));

    this.deviceTypeService
      .query()
      .pipe(map((res: HttpResponse<IDeviceType[]>) => res.body ?? []))
      .pipe(
        map((deviceTypes: IDeviceType[]) =>
          this.deviceTypeService.addDeviceTypeToCollectionIfMissing<IDeviceType>(deviceTypes, this.device?.idDeviceType)
        )
      )
      .subscribe((deviceTypes: IDeviceType[]) => (this.deviceTypesSharedCollection = deviceTypes));

    this.formulaService
      .query()
      .pipe(map((res: HttpResponse<IFormula[]>) => res.body ?? []))
      .pipe(
        map((formulas: IFormula[]) =>
          this.formulaService.addFormulaToCollectionIfMissing<IFormula>(
            formulas,
            this.device?.idFormulaHandpay,
            this.device?.idFormulaJackpot
          )
        )
      )
      .subscribe((formulas: IFormula[]) => (this.formulasSharedCollection = formulas));

    this.islandService
      .query()
      .pipe(map((res: HttpResponse<IIsland[]>) => res.body ?? []))
      .pipe(map((islands: IIsland[]) => this.islandService.addIslandToCollectionIfMissing<IIsland>(islands, this.device?.idIsland)))
      .subscribe((islands: IIsland[]) => (this.islandsSharedCollection = islands));

    this.currencyTypeService
      .query()
      .pipe(map((res: HttpResponse<ICurrencyType[]>) => res.body ?? []))
      .pipe(
        map((currencyTypes: ICurrencyType[]) =>
          this.currencyTypeService.addCurrencyTypeToCollectionIfMissing<ICurrencyType>(currencyTypes, this.device?.idCurrencyType)
        )
      )
      .subscribe((currencyTypes: ICurrencyType[]) => (this.currencyTypesSharedCollection = currencyTypes));
  }
}
