import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MunicipalityFormService } from './municipality-form.service';
import { MunicipalityService } from '../service/municipality.service';
import { IMunicipality } from '../municipality.model';
import { IDepartment } from 'app/entities/department/department.model';
import { DepartmentService } from 'app/entities/department/service/department.service';

import { MunicipalityUpdateComponent } from './municipality-update.component';

describe('Municipality Management Update Component', () => {
  let comp: MunicipalityUpdateComponent;
  let fixture: ComponentFixture<MunicipalityUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let municipalityFormService: MunicipalityFormService;
  let municipalityService: MunicipalityService;
  let departmentService: DepartmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MunicipalityUpdateComponent],
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
      .overrideTemplate(MunicipalityUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MunicipalityUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    municipalityFormService = TestBed.inject(MunicipalityFormService);
    municipalityService = TestBed.inject(MunicipalityService);
    departmentService = TestBed.inject(DepartmentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Department query and add missing value', () => {
      const municipality: IMunicipality = { id: 456 };
      const department: IDepartment = { id: 69989 };
      municipality.department = department;

      const departmentCollection: IDepartment[] = [{ id: 67007 }];
      jest.spyOn(departmentService, 'query').mockReturnValue(of(new HttpResponse({ body: departmentCollection })));
      const additionalDepartments = [department];
      const expectedCollection: IDepartment[] = [...additionalDepartments, ...departmentCollection];
      jest.spyOn(departmentService, 'addDepartmentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ municipality });
      comp.ngOnInit();

      expect(departmentService.query).toHaveBeenCalled();
      expect(departmentService.addDepartmentToCollectionIfMissing).toHaveBeenCalledWith(
        departmentCollection,
        ...additionalDepartments.map(expect.objectContaining)
      );
      expect(comp.departmentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const municipality: IMunicipality = { id: 456 };
      const department: IDepartment = { id: 71719 };
      municipality.department = department;

      activatedRoute.data = of({ municipality });
      comp.ngOnInit();

      expect(comp.departmentsSharedCollection).toContain(department);
      expect(comp.municipality).toEqual(municipality);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMunicipality>>();
      const municipality = { id: 123 };
      jest.spyOn(municipalityFormService, 'getMunicipality').mockReturnValue(municipality);
      jest.spyOn(municipalityService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ municipality });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: municipality }));
      saveSubject.complete();

      // THEN
      expect(municipalityFormService.getMunicipality).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(municipalityService.update).toHaveBeenCalledWith(expect.objectContaining(municipality));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMunicipality>>();
      const municipality = { id: 123 };
      jest.spyOn(municipalityFormService, 'getMunicipality').mockReturnValue({ id: null });
      jest.spyOn(municipalityService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ municipality: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: municipality }));
      saveSubject.complete();

      // THEN
      expect(municipalityFormService.getMunicipality).toHaveBeenCalled();
      expect(municipalityService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMunicipality>>();
      const municipality = { id: 123 };
      jest.spyOn(municipalityService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ municipality });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(municipalityService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDepartment', () => {
      it('Should forward to departmentService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(departmentService, 'compareDepartment');
        comp.compareDepartment(entity, entity2);
        expect(departmentService.compareDepartment).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
