import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DeviceFormService } from './device-form.service';
import { DeviceService } from '../service/device.service';
import { IDevice } from '../device.model';
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

import { DeviceUpdateComponent } from './device-update.component';

describe('Device Management Update Component', () => {
  let comp: DeviceUpdateComponent;
  let fixture: ComponentFixture<DeviceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let deviceFormService: DeviceFormService;
  let deviceService: DeviceService;
  let interfacingService: InterfacingService;
  let modelService: ModelService;
  let deviceCategoryService: DeviceCategoryService;
  let deviceTypeService: DeviceTypeService;
  let formulaService: FormulaService;
  let islandService: IslandService;
  let currencyTypeService: CurrencyTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DeviceUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DeviceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DeviceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    deviceFormService = TestBed.inject(DeviceFormService);
    deviceService = TestBed.inject(DeviceService);
    interfacingService = TestBed.inject(InterfacingService);
    modelService = TestBed.inject(ModelService);
    deviceCategoryService = TestBed.inject(DeviceCategoryService);
    deviceTypeService = TestBed.inject(DeviceTypeService);
    formulaService = TestBed.inject(FormulaService);
    islandService = TestBed.inject(IslandService);
    currencyTypeService = TestBed.inject(CurrencyTypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Interfacing query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const interfacing: IInterfacing = { id: 41653 };
      device.interfacing = interfacing;

      const interfacingCollection: IInterfacing[] = [{ id: 33257 }];
      jest.spyOn(interfacingService, 'query').mockReturnValue(of(new HttpResponse({ body: interfacingCollection })));
      const additionalInterfacings = [interfacing];
      const expectedCollection: IInterfacing[] = [...additionalInterfacings, ...interfacingCollection];
      jest.spyOn(interfacingService, 'addInterfacingToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(interfacingService.query).toHaveBeenCalled();
      expect(interfacingService.addInterfacingToCollectionIfMissing).toHaveBeenCalledWith(
        interfacingCollection,
        ...additionalInterfacings.map(expect.objectContaining)
      );
      expect(comp.interfacingsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Model query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const model: IModel = { id: 97184 };
      device.model = model;

      const modelCollection: IModel[] = [{ id: 35662 }];
      jest.spyOn(modelService, 'query').mockReturnValue(of(new HttpResponse({ body: modelCollection })));
      const additionalModels = [model];
      const expectedCollection: IModel[] = [...additionalModels, ...modelCollection];
      jest.spyOn(modelService, 'addModelToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(modelService.query).toHaveBeenCalled();
      expect(modelService.addModelToCollectionIfMissing).toHaveBeenCalledWith(
        modelCollection,
        ...additionalModels.map(expect.objectContaining)
      );
      expect(comp.modelsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DeviceCategory query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const deviceCategory: IDeviceCategory = { id: 87654 };
      device.deviceCategory = deviceCategory;

      const deviceCategoryCollection: IDeviceCategory[] = [{ id: 49750 }];
      jest.spyOn(deviceCategoryService, 'query').mockReturnValue(of(new HttpResponse({ body: deviceCategoryCollection })));
      const additionalDeviceCategories = [deviceCategory];
      const expectedCollection: IDeviceCategory[] = [...additionalDeviceCategories, ...deviceCategoryCollection];
      jest.spyOn(deviceCategoryService, 'addDeviceCategoryToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(deviceCategoryService.query).toHaveBeenCalled();
      expect(deviceCategoryService.addDeviceCategoryToCollectionIfMissing).toHaveBeenCalledWith(
        deviceCategoryCollection,
        ...additionalDeviceCategories.map(expect.objectContaining)
      );
      expect(comp.deviceCategoriesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DeviceType query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const deviceType: IDeviceType = { id: 21522 };
      device.deviceType = deviceType;

      const deviceTypeCollection: IDeviceType[] = [{ id: 47273 }];
      jest.spyOn(deviceTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: deviceTypeCollection })));
      const additionalDeviceTypes = [deviceType];
      const expectedCollection: IDeviceType[] = [...additionalDeviceTypes, ...deviceTypeCollection];
      jest.spyOn(deviceTypeService, 'addDeviceTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(deviceTypeService.query).toHaveBeenCalled();
      expect(deviceTypeService.addDeviceTypeToCollectionIfMissing).toHaveBeenCalledWith(
        deviceTypeCollection,
        ...additionalDeviceTypes.map(expect.objectContaining)
      );
      expect(comp.deviceTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Formula query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const formulaHandpay: IFormula = { id: 25714 };
      device.formulaHandpay = formulaHandpay;
      const formulaJackpot: IFormula = { id: 36260 };
      device.formulaJackpot = formulaJackpot;

      const formulaCollection: IFormula[] = [{ id: 21701 }];
      jest.spyOn(formulaService, 'query').mockReturnValue(of(new HttpResponse({ body: formulaCollection })));
      const additionalFormulas = [formulaHandpay, formulaJackpot];
      const expectedCollection: IFormula[] = [...additionalFormulas, ...formulaCollection];
      jest.spyOn(formulaService, 'addFormulaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(formulaService.query).toHaveBeenCalled();
      expect(formulaService.addFormulaToCollectionIfMissing).toHaveBeenCalledWith(
        formulaCollection,
        ...additionalFormulas.map(expect.objectContaining)
      );
      expect(comp.formulasSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Island query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const island: IIsland = { id: 38293 };
      device.island = island;

      const islandCollection: IIsland[] = [{ id: 12174 }];
      jest.spyOn(islandService, 'query').mockReturnValue(of(new HttpResponse({ body: islandCollection })));
      const additionalIslands = [island];
      const expectedCollection: IIsland[] = [...additionalIslands, ...islandCollection];
      jest.spyOn(islandService, 'addIslandToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(islandService.query).toHaveBeenCalled();
      expect(islandService.addIslandToCollectionIfMissing).toHaveBeenCalledWith(
        islandCollection,
        ...additionalIslands.map(expect.objectContaining)
      );
      expect(comp.islandsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call CurrencyType query and add missing value', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const urrencyType: ICurrencyType = { id: 37094 };
      device.urrencyType = urrencyType;

      const currencyTypeCollection: ICurrencyType[] = [{ id: 34860 }];
      jest.spyOn(currencyTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: currencyTypeCollection })));
      const additionalCurrencyTypes = [urrencyType];
      const expectedCollection: ICurrencyType[] = [...additionalCurrencyTypes, ...currencyTypeCollection];
      jest.spyOn(currencyTypeService, 'addCurrencyTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(currencyTypeService.query).toHaveBeenCalled();
      expect(currencyTypeService.addCurrencyTypeToCollectionIfMissing).toHaveBeenCalledWith(
        currencyTypeCollection,
        ...additionalCurrencyTypes.map(expect.objectContaining)
      );
      expect(comp.currencyTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const device: IDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const interfacing: IInterfacing = { id: 19370 };
      device.interfacing = interfacing;
      const model: IModel = { id: 70112 };
      device.model = model;
      const deviceCategory: IDeviceCategory = { id: 22759 };
      device.deviceCategory = deviceCategory;
      const deviceType: IDeviceType = { id: 4674 };
      device.deviceType = deviceType;
      const formulaHandpay: IFormula = { id: 64416 };
      device.formulaHandpay = formulaHandpay;
      const formulaJackpot: IFormula = { id: 36010 };
      device.formulaJackpot = formulaJackpot;
      const island: IIsland = { id: 28163 };
      device.island = island;
      const urrencyType: ICurrencyType = { id: 12967 };
      device.urrencyType = urrencyType;

      activatedRoute.data = of({ device });
      comp.ngOnInit();

      expect(comp.interfacingsSharedCollection).toContain(interfacing);
      expect(comp.modelsSharedCollection).toContain(model);
      expect(comp.deviceCategoriesSharedCollection).toContain(deviceCategory);
      expect(comp.deviceTypesSharedCollection).toContain(deviceType);
      expect(comp.formulasSharedCollection).toContain(formulaHandpay);
      expect(comp.formulasSharedCollection).toContain(formulaJackpot);
      expect(comp.islandsSharedCollection).toContain(island);
      expect(comp.currencyTypesSharedCollection).toContain(urrencyType);
      expect(comp.device).toEqual(device);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDevice>>();
      const device = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceFormService, 'getDevice').mockReturnValue(device);
      jest.spyOn(deviceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ device });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: device }));
      saveSubject.complete();

      // THEN
      expect(deviceFormService.getDevice).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(deviceService.update).toHaveBeenCalledWith(expect.objectContaining(device));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDevice>>();
      const device = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceFormService, 'getDevice').mockReturnValue({ id: null });
      jest.spyOn(deviceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ device: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: device }));
      saveSubject.complete();

      // THEN
      expect(deviceFormService.getDevice).toHaveBeenCalled();
      expect(deviceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDevice>>();
      const device = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ device });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(deviceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareInterfacing', () => {
      it('Should forward to interfacingService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(interfacingService, 'compareInterfacing');
        comp.compareInterfacing(entity, entity2);
        expect(interfacingService.compareInterfacing).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareModel', () => {
      it('Should forward to modelService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(modelService, 'compareModel');
        comp.compareModel(entity, entity2);
        expect(modelService.compareModel).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareDeviceCategory', () => {
      it('Should forward to deviceCategoryService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(deviceCategoryService, 'compareDeviceCategory');
        comp.compareDeviceCategory(entity, entity2);
        expect(deviceCategoryService.compareDeviceCategory).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareDeviceType', () => {
      it('Should forward to deviceTypeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(deviceTypeService, 'compareDeviceType');
        comp.compareDeviceType(entity, entity2);
        expect(deviceTypeService.compareDeviceType).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareFormula', () => {
      it('Should forward to formulaService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(formulaService, 'compareFormula');
        comp.compareFormula(entity, entity2);
        expect(formulaService.compareFormula).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareIsland', () => {
      it('Should forward to islandService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(islandService, 'compareIsland');
        comp.compareIsland(entity, entity2);
        expect(islandService.compareIsland).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareCurrencyType', () => {
      it('Should forward to currencyTypeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(currencyTypeService, 'compareCurrencyType');
        comp.compareCurrencyType(entity, entity2);
        expect(currencyTypeService.compareCurrencyType).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
