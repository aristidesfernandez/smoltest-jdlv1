import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDeviceEstablishment } from '../device-establishment.model';
import { DeviceEstablishmentService } from '../service/device-establishment.service';

import { DeviceEstablishmentRoutingResolveService } from './device-establishment-routing-resolve.service';

describe('DeviceEstablishment routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DeviceEstablishmentRoutingResolveService;
  let service: DeviceEstablishmentService;
  let resultDeviceEstablishment: IDeviceEstablishment | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(DeviceEstablishmentRoutingResolveService);
    service = TestBed.inject(DeviceEstablishmentService);
    resultDeviceEstablishment = undefined;
  });

  describe('resolve', () => {
    it('should return IDeviceEstablishment returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDeviceEstablishment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDeviceEstablishment).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDeviceEstablishment = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDeviceEstablishment).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IDeviceEstablishment>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDeviceEstablishment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDeviceEstablishment).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
