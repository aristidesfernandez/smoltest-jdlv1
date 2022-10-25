import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IInterfacing } from '../interfacing.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../interfacing.test-samples';

import { InterfacingService } from './interfacing.service';

const requireRestSample: IInterfacing = {
  ...sampleWithRequiredData,
};

describe('Interfacing Service', () => {
  let service: InterfacingService;
  let httpMock: HttpTestingController;
  let expectedResult: IInterfacing | IInterfacing[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(InterfacingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Interfacing', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const interfacing = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(interfacing).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Interfacing', () => {
      const interfacing = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(interfacing).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Interfacing', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Interfacing', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Interfacing', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addInterfacingToCollectionIfMissing', () => {
      it('should add a Interfacing to an empty array', () => {
        const interfacing: IInterfacing = sampleWithRequiredData;
        expectedResult = service.addInterfacingToCollectionIfMissing([], interfacing);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(interfacing);
      });

      it('should not add a Interfacing to an array that contains it', () => {
        const interfacing: IInterfacing = sampleWithRequiredData;
        const interfacingCollection: IInterfacing[] = [
          {
            ...interfacing,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addInterfacingToCollectionIfMissing(interfacingCollection, interfacing);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Interfacing to an array that doesn't contain it", () => {
        const interfacing: IInterfacing = sampleWithRequiredData;
        const interfacingCollection: IInterfacing[] = [sampleWithPartialData];
        expectedResult = service.addInterfacingToCollectionIfMissing(interfacingCollection, interfacing);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(interfacing);
      });

      it('should add only unique Interfacing to an array', () => {
        const interfacingArray: IInterfacing[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const interfacingCollection: IInterfacing[] = [sampleWithRequiredData];
        expectedResult = service.addInterfacingToCollectionIfMissing(interfacingCollection, ...interfacingArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const interfacing: IInterfacing = sampleWithRequiredData;
        const interfacing2: IInterfacing = sampleWithPartialData;
        expectedResult = service.addInterfacingToCollectionIfMissing([], interfacing, interfacing2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(interfacing);
        expect(expectedResult).toContain(interfacing2);
      });

      it('should accept null and undefined values', () => {
        const interfacing: IInterfacing = sampleWithRequiredData;
        expectedResult = service.addInterfacingToCollectionIfMissing([], null, interfacing, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(interfacing);
      });

      it('should return initial array if no Interfacing is added', () => {
        const interfacingCollection: IInterfacing[] = [sampleWithRequiredData];
        expectedResult = service.addInterfacingToCollectionIfMissing(interfacingCollection, undefined, null);
        expect(expectedResult).toEqual(interfacingCollection);
      });
    });

    describe('compareInterfacing', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareInterfacing(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareInterfacing(entity1, entity2);
        const compareResult2 = service.compareInterfacing(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareInterfacing(entity1, entity2);
        const compareResult2 = service.compareInterfacing(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareInterfacing(entity1, entity2);
        const compareResult2 = service.compareInterfacing(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
