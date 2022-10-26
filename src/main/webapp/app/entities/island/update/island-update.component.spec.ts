import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IslandFormService } from './island-form.service';
import { IslandService } from '../service/island.service';
import { IIsland } from '../island.model';

import { IslandUpdateComponent } from './island-update.component';

describe('Island Management Update Component', () => {
  let comp: IslandUpdateComponent;
  let fixture: ComponentFixture<IslandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let islandFormService: IslandFormService;
  let islandService: IslandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [IslandUpdateComponent],
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
      .overrideTemplate(IslandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(IslandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    islandFormService = TestBed.inject(IslandFormService);
    islandService = TestBed.inject(IslandService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const island: IIsland = { id: 456 };

      activatedRoute.data = of({ island });
      comp.ngOnInit();

      expect(comp.island).toEqual(island);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIsland>>();
      const island = { id: 123 };
      jest.spyOn(islandFormService, 'getIsland').mockReturnValue(island);
      jest.spyOn(islandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ island });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: island }));
      saveSubject.complete();

      // THEN
      expect(islandFormService.getIsland).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(islandService.update).toHaveBeenCalledWith(expect.objectContaining(island));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIsland>>();
      const island = { id: 123 };
      jest.spyOn(islandFormService, 'getIsland').mockReturnValue({ id: null });
      jest.spyOn(islandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ island: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: island }));
      saveSubject.complete();

      // THEN
      expect(islandFormService.getIsland).toHaveBeenCalled();
      expect(islandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IIsland>>();
      const island = { id: 123 };
      jest.spyOn(islandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ island });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(islandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
