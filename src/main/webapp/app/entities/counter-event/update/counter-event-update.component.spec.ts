import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CounterEventFormService } from './counter-event-form.service';
import { CounterEventService } from '../service/counter-event.service';
import { ICounterEvent } from '../counter-event.model';
import { ICounterType } from 'app/entities/counter-type/counter-type.model';
import { CounterTypeService } from 'app/entities/counter-type/service/counter-type.service';
import { IEventDevice } from 'app/entities/event-device/event-device.model';
import { EventDeviceService } from 'app/entities/event-device/service/event-device.service';

import { CounterEventUpdateComponent } from './counter-event-update.component';

describe('CounterEvent Management Update Component', () => {
  let comp: CounterEventUpdateComponent;
  let fixture: ComponentFixture<CounterEventUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let counterEventFormService: CounterEventFormService;
  let counterEventService: CounterEventService;
  let counterTypeService: CounterTypeService;
  let eventDeviceService: EventDeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CounterEventUpdateComponent],
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
      .overrideTemplate(CounterEventUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CounterEventUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    counterEventFormService = TestBed.inject(CounterEventFormService);
    counterEventService = TestBed.inject(CounterEventService);
    counterTypeService = TestBed.inject(CounterTypeService);
    eventDeviceService = TestBed.inject(EventDeviceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call CounterType query and add missing value', () => {
      const counterEvent: ICounterEvent = { id: 456 };
      const counterCode: ICounterType = { id: 34682 };
      counterEvent.counterCode = counterCode;

      const counterTypeCollection: ICounterType[] = [{ id: 36567 }];
      jest.spyOn(counterTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: counterTypeCollection })));
      const additionalCounterTypes = [counterCode];
      const expectedCollection: ICounterType[] = [...additionalCounterTypes, ...counterTypeCollection];
      jest.spyOn(counterTypeService, 'addCounterTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ counterEvent });
      comp.ngOnInit();

      expect(counterTypeService.query).toHaveBeenCalled();
      expect(counterTypeService.addCounterTypeToCollectionIfMissing).toHaveBeenCalledWith(
        counterTypeCollection,
        ...additionalCounterTypes.map(expect.objectContaining)
      );
      expect(comp.counterTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call EventDevice query and add missing value', () => {
      const counterEvent: ICounterEvent = { id: 456 };
      const idEventDevice: IEventDevice = { id: 73158 };
      counterEvent.idEventDevice = idEventDevice;

      const eventDeviceCollection: IEventDevice[] = [{ id: 43369 }];
      jest.spyOn(eventDeviceService, 'query').mockReturnValue(of(new HttpResponse({ body: eventDeviceCollection })));
      const additionalEventDevices = [idEventDevice];
      const expectedCollection: IEventDevice[] = [...additionalEventDevices, ...eventDeviceCollection];
      jest.spyOn(eventDeviceService, 'addEventDeviceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ counterEvent });
      comp.ngOnInit();

      expect(eventDeviceService.query).toHaveBeenCalled();
      expect(eventDeviceService.addEventDeviceToCollectionIfMissing).toHaveBeenCalledWith(
        eventDeviceCollection,
        ...additionalEventDevices.map(expect.objectContaining)
      );
      expect(comp.eventDevicesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const counterEvent: ICounterEvent = { id: 456 };
      const counterCode: ICounterType = { id: 68680 };
      counterEvent.counterCode = counterCode;
      const idEventDevice: IEventDevice = { id: 22670 };
      counterEvent.idEventDevice = idEventDevice;

      activatedRoute.data = of({ counterEvent });
      comp.ngOnInit();

      expect(comp.counterTypesSharedCollection).toContain(counterCode);
      expect(comp.eventDevicesSharedCollection).toContain(idEventDevice);
      expect(comp.counterEvent).toEqual(counterEvent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterEvent>>();
      const counterEvent = { id: 123 };
      jest.spyOn(counterEventFormService, 'getCounterEvent').mockReturnValue(counterEvent);
      jest.spyOn(counterEventService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterEvent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: counterEvent }));
      saveSubject.complete();

      // THEN
      expect(counterEventFormService.getCounterEvent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(counterEventService.update).toHaveBeenCalledWith(expect.objectContaining(counterEvent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterEvent>>();
      const counterEvent = { id: 123 };
      jest.spyOn(counterEventFormService, 'getCounterEvent').mockReturnValue({ id: null });
      jest.spyOn(counterEventService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterEvent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: counterEvent }));
      saveSubject.complete();

      // THEN
      expect(counterEventFormService.getCounterEvent).toHaveBeenCalled();
      expect(counterEventService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICounterEvent>>();
      const counterEvent = { id: 123 };
      jest.spyOn(counterEventService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ counterEvent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(counterEventService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCounterType', () => {
      it('Should forward to counterTypeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(counterTypeService, 'compareCounterType');
        comp.compareCounterType(entity, entity2);
        expect(counterTypeService.compareCounterType).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareEventDevice', () => {
      it('Should forward to eventDeviceService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(eventDeviceService, 'compareEventDevice');
        comp.compareEventDevice(entity, entity2);
        expect(eventDeviceService.compareEventDevice).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
