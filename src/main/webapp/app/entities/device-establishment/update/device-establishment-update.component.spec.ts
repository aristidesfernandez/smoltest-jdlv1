import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DeviceEstablishmentFormService } from './device-establishment-form.service';
import { DeviceEstablishmentService } from '../service/device-establishment.service';
import { IDeviceEstablishment } from '../device-establishment.model';
import { IEstablishment } from 'app/entities/establishment/establishment.model';
import { EstablishmentService } from 'app/entities/establishment/service/establishment.service';

import { DeviceEstablishmentUpdateComponent } from './device-establishment-update.component';

describe('DeviceEstablishment Management Update Component', () => {
  let comp: DeviceEstablishmentUpdateComponent;
  let fixture: ComponentFixture<DeviceEstablishmentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let deviceEstablishmentFormService: DeviceEstablishmentFormService;
  let deviceEstablishmentService: DeviceEstablishmentService;
  let establishmentService: EstablishmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DeviceEstablishmentUpdateComponent],
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
      .overrideTemplate(DeviceEstablishmentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DeviceEstablishmentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    deviceEstablishmentFormService = TestBed.inject(DeviceEstablishmentFormService);
    deviceEstablishmentService = TestBed.inject(DeviceEstablishmentService);
    establishmentService = TestBed.inject(EstablishmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Establishment query and add missing value', () => {
      const deviceEstablishment: IDeviceEstablishment = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const idEstablishment: IEstablishment = { id: 63028 };
      deviceEstablishment.idEstablishment = idEstablishment;

      const establishmentCollection: IEstablishment[] = [{ id: 29200 }];
      jest.spyOn(establishmentService, 'query').mockReturnValue(of(new HttpResponse({ body: establishmentCollection })));
      const additionalEstablishments = [idEstablishment];
      const expectedCollection: IEstablishment[] = [...additionalEstablishments, ...establishmentCollection];
      jest.spyOn(establishmentService, 'addEstablishmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ deviceEstablishment });
      comp.ngOnInit();

      expect(establishmentService.query).toHaveBeenCalled();
      expect(establishmentService.addEstablishmentToCollectionIfMissing).toHaveBeenCalledWith(
        establishmentCollection,
        ...additionalEstablishments.map(expect.objectContaining)
      );
      expect(comp.establishmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const deviceEstablishment: IDeviceEstablishment = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const idEstablishment: IEstablishment = { id: 5885 };
      deviceEstablishment.idEstablishment = idEstablishment;

      activatedRoute.data = of({ deviceEstablishment });
      comp.ngOnInit();

      expect(comp.establishmentsSharedCollection).toContain(idEstablishment);
      expect(comp.deviceEstablishment).toEqual(deviceEstablishment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeviceEstablishment>>();
      const deviceEstablishment = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceEstablishmentFormService, 'getDeviceEstablishment').mockReturnValue(deviceEstablishment);
      jest.spyOn(deviceEstablishmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deviceEstablishment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: deviceEstablishment }));
      saveSubject.complete();

      // THEN
      expect(deviceEstablishmentFormService.getDeviceEstablishment).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(deviceEstablishmentService.update).toHaveBeenCalledWith(expect.objectContaining(deviceEstablishment));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeviceEstablishment>>();
      const deviceEstablishment = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceEstablishmentFormService, 'getDeviceEstablishment').mockReturnValue({ id: null });
      jest.spyOn(deviceEstablishmentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deviceEstablishment: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: deviceEstablishment }));
      saveSubject.complete();

      // THEN
      expect(deviceEstablishmentFormService.getDeviceEstablishment).toHaveBeenCalled();
      expect(deviceEstablishmentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeviceEstablishment>>();
      const deviceEstablishment = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(deviceEstablishmentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deviceEstablishment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(deviceEstablishmentService.update).toHaveBeenCalled();
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
