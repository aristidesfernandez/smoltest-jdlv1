import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CounterDeviceFormService } from './counter-device-form.service';
import { CounterDeviceService } from '../service/counter-device.service';
import { ICounterDevice } from '../counter-device.model';
import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { CounterTypeService } from 'app/entities/counter-type/service/counter-type.service';
import { IDevice } from 'app/entities/device/device.model';
import { DeviceService } from 'app/entities/device/service/device.service';

import { CounterDeviceUpdateComponent } from './counter-device-update.component';

describe('CounterDevice Management Update Component', () => {
  let comp: CounterDeviceUpdateComponent;
  let fixture: ComponentFixture<CounterDeviceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let counterDeviceFormService: CounterDeviceFormService;
  let counterDeviceService: CounterDeviceService;
  let counterTypeService: CounterTypeService;
  let deviceService: DeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CounterDeviceUpdateComponent],
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
      .overrideTemplate(CounterDeviceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CounterDeviceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    counterDeviceFormService = TestBed.inject(CounterDeviceFormService);
    counterDeviceService = TestBed.inject(CounterDeviceService);
    counterTypeService = TestBed.inject(CounterTypeService);
    deviceService = TestBed.inject(DeviceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call CounterType query and add missing value', () => {
      const counterDevice: ICounterDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const counterCode: ICounterType = { counterCode: 'd1cf459f-8e49-46c3-a0de-8c6b74b30271' };
      counterDevice.counterCode = counterCode;

      const counterTypeCollection: ICounterType[] = [{ counterCode: '7fed1947-3eb7-4d5a-b8e3-968958c3219a' }];
      jest.spyOn(counterTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: counterTypeCollection })));
      const additionalCounterTypes = [counterCode];
      const expectedCollection: ICounterType[] = [...additionalCounterTypes, ...counterTypeCollection];
      jest.spyOn(counterTypeService, 'addCounterTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ counterDevice });
      comp.ngOnInit();

      expect(counterTypeService.query).toHaveBeenCalled();
      expect(counterTypeService.addCounterTypeToCollectionIfMissing).toHaveBeenCalledWith(
        counterTypeCollection,
        ...additionalCounterTypes.map(expect.objectContaining)
      );
      expect(comp.counterTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Device query and add missing value', () => {
      const counterDevice: ICounterDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const idDevice: IDevice = { id: '980d8a38-7f2c-40bf-af5e-8ab26e5864d9' };
      counterDevice.idDevice = idDevice;

      const deviceCollection: IDevice[] = [{ id: '1b61153d-8766-4a43-8f32-f9265b7ad966' }];
      jest.spyOn(deviceService, 'query').mockReturnValue(of(new HttpResponse({ body: deviceCollection })));
      const additionalDevices = [idDevice];
      const expectedCollection: IDevice[] = [...additionalDevices, ...deviceCollection];
      jest.spyOn(deviceService, 'addDeviceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ counterDevice });
      comp.ngOnInit();

      expect(deviceService.query).toHaveBeenCalled();
      expect(deviceService.addDeviceToCollectionIfMissing).toHaveBeenCalledWith(
        deviceCollection,
        ...additionalDevices.map(expect.objectContaining)
      );
      expect(comp.devicesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const counterDevice: ICounterDevice = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const counterCode: ICounterType = { counterCode: '0577a86c-91d6-4c83-ba61-a795d49c187b' };
      counterDevice.counterCode = counterCode;
      const idDevice: IDevice = { id: '1388d53f-7330-48c3-bac8-9eedcbef8210' };
      counterDevice.idDevice = idDevice;

      activatedRoute.data = of({ counterDevice });
      comp.ngOnInit();

      expect(comp.counterTypesSharedCollection).toContain(counterCode);
      expect(comp.devicesSharedCollection).toContain(idDevice);
      expect(comp.counterDevice).toEqual(counterDevice);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterDevice>>();
      const counterDevice = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(counterDeviceFormService, 'getCounterDevice').mockReturnValue(counterDevice);
      jest.spyOn(counterDeviceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterDevice });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: counterDevice }));
      saveSubject.complete();

      // THEN
      expect(counterDeviceFormService.getCounterDevice).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(counterDeviceService.update).toHaveBeenCalledWith(expect.objectContaining(counterDevice));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterDevice>>();
      const counterDevice = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(counterDeviceFormService, 'getCounterDevice').mockReturnValue({ id: null });
      jest.spyOn(counterDeviceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterDevice: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: counterDevice }));
      saveSubject.complete();

      // THEN
      expect(counterDeviceFormService.getCounterDevice).toHaveBeenCalled();
      expect(counterDeviceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterDevice>>();
      const counterDevice = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(counterDeviceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterDevice });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(counterDeviceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCounterType', () => {
      it('Should forward to counterTypeService', () => {
        const entity = { counterCode: 'ABC' };
        const entity2 = { counterCode: 'CBA' };
        jest.spyOn(counterTypeService, 'compareCounterType');
        comp.compareCounterType(entity, entity2);
        expect(counterTypeService.compareCounterType).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareDevice', () => {
      it('Should forward to deviceService', () => {
        const entity = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
        const entity2 = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
        jest.spyOn(deviceService, 'compareDevice');
        comp.compareDevice(entity, entity2);
        expect(deviceService.compareDevice).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
