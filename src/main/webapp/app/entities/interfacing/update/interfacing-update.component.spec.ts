import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { InterfacingFormService } from './interfacing-form.service';
import { InterfacingService } from '../service/interfacing.service';
import { IInterfacing } from '../interfacing.model';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { EstablishmentService } from 'app/entities/establishment/service/establishment.service';

import { InterfacingUpdateComponent } from './interfacing-update.component';

describe('Interfacing Management Update Component', () => {
  let comp: InterfacingUpdateComponent;
  let fixture: ComponentFixture<InterfacingUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let interfacingFormService: InterfacingFormService;
  let interfacingService: InterfacingService;
  let establishmentService: EstablishmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [InterfacingUpdateComponent],
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
      .overrideTemplate(InterfacingUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(InterfacingUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    interfacingFormService = TestBed.inject(InterfacingFormService);
    interfacingService = TestBed.inject(InterfacingService);
    establishmentService = TestBed.inject(EstablishmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Establishment query and add missing value', () => {
      const interfacing: IInterfacing = { id: 456 };
      const establishment: IEstablishment = { id: 95790 };
      interfacing.establishment = establishment;

      const establishmentCollection: IEstablishment[] = [{ id: 18748 }];
      jest.spyOn(establishmentService, 'query').mockReturnValue(of(new HttpResponse({ body: establishmentCollection })));
      const additionalEstablishments = [establishment];
      const expectedCollection: IEstablishment[] = [...additionalEstablishments, ...establishmentCollection];
      jest.spyOn(establishmentService, 'addEstablishmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ interfacing });
      comp.ngOnInit();

      expect(establishmentService.query).toHaveBeenCalled();
      expect(establishmentService.addEstablishmentToCollectionIfMissing).toHaveBeenCalledWith(
        establishmentCollection,
        ...additionalEstablishments.map(expect.objectContaining)
      );
      expect(comp.establishmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const interfacing: IInterfacing = { id: 456 };
      const establishment: IEstablishment = { id: 56455 };
      interfacing.establishment = establishment;

      activatedRoute.data = of({ interfacing });
      comp.ngOnInit();

      expect(comp.establishmentsSharedCollection).toContain(establishment);
      expect(comp.interfacing).toEqual(interfacing);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInterfacing>>();
      const interfacing = { id: 123 };
      jest.spyOn(interfacingFormService, 'getInterfacing').mockReturnValue(interfacing);
      jest.spyOn(interfacingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ interfacing });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: interfacing }));
      saveSubject.complete();

      // THEN
      expect(interfacingFormService.getInterfacing).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(interfacingService.update).toHaveBeenCalledWith(expect.objectContaining(interfacing));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInterfacing>>();
      const interfacing = { id: 123 };
      jest.spyOn(interfacingFormService, 'getInterfacing').mockReturnValue({ id: null });
      jest.spyOn(interfacingService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ interfacing: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: interfacing }));
      saveSubject.complete();

      // THEN
      expect(interfacingFormService.getInterfacing).toHaveBeenCalled();
      expect(interfacingService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IInterfacing>>();
      const interfacing = { id: 123 };
      jest.spyOn(interfacingService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ interfacing });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(interfacingService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEstablishment', () => {
      it('Should forward to establishmentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(establishmentService, 'compareEstablishment');
        comp.compareEstablishment(entity, entity2);
        expect(establishmentService.compareEstablishment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
