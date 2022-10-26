import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../interfacing.test-samples';

import { InterfacingFormService } from './interfacing-form.service';

describe('Interfacing Form Service', () => {
  let service: InterfacingFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InterfacingFormService);
  });

  describe('Service methods', () => {
    describe('createInterfacingFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createInterfacingFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            isAssigned: expect.any(Object),
            ipAddress: expect.any(Object),
            hash: expect.any(Object),
            serial: expect.any(Object),
            version: expect.any(Object),
            port: expect.any(Object),
            establishment: expect.any(Object),
          })
        );
      });

      it('passing IInterfacing should create a new form with FormGroup', () => {
        const formGroup = service.createInterfacingFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            isAssigned: expect.any(Object),
            ipAddress: expect.any(Object),
            hash: expect.any(Object),
            serial: expect.any(Object),
            version: expect.any(Object),
            port: expect.any(Object),
            establishment: expect.any(Object),
          })
        );
      });
    });

    describe('getInterfacing', () => {
      it('should return NewInterfacing for default Interfacing initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createInterfacingFormGroup(sampleWithNewData);

        const interfacing = service.getInterfacing(formGroup) as any;

        expect(interfacing).toMatchObject(sampleWithNewData);
      });

      it('should return NewInterfacing for empty Interfacing initial value', () => {
        const formGroup = service.createInterfacingFormGroup();

        const interfacing = service.getInterfacing(formGroup) as any;

        expect(interfacing).toMatchObject({});
      });

      it('should return IInterfacing', () => {
        const formGroup = service.createInterfacingFormGroup(sampleWithRequiredData);

        const interfacing = service.getInterfacing(formGroup) as any;

        expect(interfacing).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IInterfacing should not enable id FormControl', () => {
        const formGroup = service.createInterfacingFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewInterfacing should disable id FormControl', () => {
        const formGroup = service.createInterfacingFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
