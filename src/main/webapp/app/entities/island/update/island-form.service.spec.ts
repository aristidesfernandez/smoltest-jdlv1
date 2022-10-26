import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../island.test-samples';

import { IslandFormService } from './island-form.service';

describe('Island Form Service', () => {
  let service: IslandFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IslandFormService);
  });

  describe('Service methods', () => {
    describe('createIslandFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createIslandFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            name: expect.any(Object),
          })
        );
      });

      it('passing IIsland should create a new form with FormGroup', () => {
        const formGroup = service.createIslandFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            description: expect.any(Object),
            name: expect.any(Object),
          })
        );
      });
    });

    describe('getIsland', () => {
      it('should return NewIsland for default Island initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createIslandFormGroup(sampleWithNewData);

        const island = service.getIsland(formGroup) as any;

        expect(island).toMatchObject(sampleWithNewData);
      });

      it('should return NewIsland for empty Island initial value', () => {
        const formGroup = service.createIslandFormGroup();

        const island = service.getIsland(formGroup) as any;

        expect(island).toMatchObject({});
      });

      it('should return IIsland', () => {
        const formGroup = service.createIslandFormGroup(sampleWithRequiredData);

        const island = service.getIsland(formGroup) as any;

        expect(island).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IIsland should not enable id FormControl', () => {
        const formGroup = service.createIslandFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewIsland should disable id FormControl', () => {
        const formGroup = service.createIslandFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
