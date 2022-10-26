import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IIsland } from '../island.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../island.test-samples';

import { IslandService } from './island.service';

const requireRestSample: IIsland = {
  ...sampleWithRequiredData,
};

describe('Island Service', () => {
  let service: IslandService;
  let httpMock: HttpTestingController;
  let expectedResult: IIsland | IIsland[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(IslandService);
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

    it('should create a Island', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const island = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(island).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Island', () => {
      const island = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(island).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Island', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Island', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Island', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addIslandToCollectionIfMissing', () => {
      it('should add a Island to an empty array', () => {
        const island: IIsland = sampleWithRequiredData;
        expectedResult = service.addIslandToCollectionIfMissing([], island);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(island);
      });

      it('should not add a Island to an array that contains it', () => {
        const island: IIsland = sampleWithRequiredData;
        const islandCollection: IIsland[] = [
          {
            ...island,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addIslandToCollectionIfMissing(islandCollection, island);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Island to an array that doesn't contain it", () => {
        const island: IIsland = sampleWithRequiredData;
        const islandCollection: IIsland[] = [sampleWithPartialData];
        expectedResult = service.addIslandToCollectionIfMissing(islandCollection, island);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(island);
      });

      it('should add only unique Island to an array', () => {
        const islandArray: IIsland[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const islandCollection: IIsland[] = [sampleWithRequiredData];
        expectedResult = service.addIslandToCollectionIfMissing(islandCollection, ...islandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const island: IIsland = sampleWithRequiredData;
        const island2: IIsland = sampleWithPartialData;
        expectedResult = service.addIslandToCollectionIfMissing([], island, island2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(island);
        expect(expectedResult).toContain(island2);
      });

      it('should accept null and undefined values', () => {
        const island: IIsland = sampleWithRequiredData;
        expectedResult = service.addIslandToCollectionIfMissing([], null, island, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(island);
      });

      it('should return initial array if no Island is added', () => {
        const islandCollection: IIsland[] = [sampleWithRequiredData];
        expectedResult = service.addIslandToCollectionIfMissing(islandCollection, undefined, null);
        expect(expectedResult).toEqual(islandCollection);
      });
    });

    describe('compareIsland', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareIsland(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareIsland(entity1, entity2);
        const compareResult2 = service.compareIsland(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareIsland(entity1, entity2);
        const compareResult2 = service.compareIsland(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareIsland(entity1, entity2);
        const compareResult2 = service.compareIsland(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
