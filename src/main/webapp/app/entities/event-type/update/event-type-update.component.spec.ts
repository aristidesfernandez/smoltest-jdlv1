import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EventTypeFormService } from './event-type-form.service';
import { EventTypeService } from '../service/event-type.service';
import { IEventType } from '../event-type.model';
import { IModel } from 'app/entities/model/model.model';
import { ModelService } from 'app/entities/model/service/model.service';

import { EventTypeUpdateComponent } from './event-type-update.component';

describe('EventType Management Update Component', () => {
  let comp: EventTypeUpdateComponent;
  let fixture: ComponentFixture<EventTypeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let eventTypeFormService: EventTypeFormService;
  let eventTypeService: EventTypeService;
  let modelService: ModelService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [EventTypeUpdateComponent],
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
      .overrideTemplate(EventTypeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EventTypeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    eventTypeFormService = TestBed.inject(EventTypeFormService);
    eventTypeService = TestBed.inject(EventTypeService);
    modelService = TestBed.inject(ModelService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Model query and add missing value', () => {
      const eventType: IEventType = { id: 456 };
      const idEventTypes: IModel[] = [{ id: 88674 }];
      eventType.idEventTypes = idEventTypes;

      const modelCollection: IModel[] = [{ id: 72566 }];
      jest.spyOn(modelService, 'query').mockReturnValue(of(new HttpResponse({ body: modelCollection })));
      const additionalModels = [...idEventTypes];
      const expectedCollection: IModel[] = [...additionalModels, ...modelCollection];
      jest.spyOn(modelService, 'addModelToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ eventType });
      comp.ngOnInit();

      expect(modelService.query).toHaveBeenCalled();
      expect(modelService.addModelToCollectionIfMissing).toHaveBeenCalledWith(
        modelCollection,
        ...additionalModels.map(expect.objectContaining)
      );
      expect(comp.modelsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const eventType: IEventType = { id: 456 };
      const idEventType: IModel = { id: 94492 };
      eventType.idEventTypes = [idEventType];

      activatedRoute.data = of({ eventType });
      comp.ngOnInit();

      expect(comp.modelsSharedCollection).toContain(idEventType);
      expect(comp.eventType).toEqual(eventType);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEventType>>();
      const eventType = { id: 123 };
      jest.spyOn(eventTypeFormService, 'getEventType').mockReturnValue(eventType);
      jest.spyOn(eventTypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ eventType });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: eventType }));
      saveSubject.complete();

      // THEN
      expect(eventTypeFormService.getEventType).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(eventTypeService.update).toHaveBeenCalledWith(expect.objectContaining(eventType));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEventType>>();
      const eventType = { id: 123 };
      jest.spyOn(eventTypeFormService, 'getEventType').mockReturnValue({ id: null });
      jest.spyOn(eventTypeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ eventType: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: eventType }));
      saveSubject.complete();

      // THEN
      expect(eventTypeFormService.getEventType).toHaveBeenCalled();
      expect(eventTypeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IEventType>>();
      const eventType = { id: 123 };
      jest.spyOn(eventTypeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ eventType });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(eventTypeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareModel', () => {
      it('Should forward to modelService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(modelService, 'compareModel');
        comp.compareModel(entity, entity2);
        expect(modelService.compareModel).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
