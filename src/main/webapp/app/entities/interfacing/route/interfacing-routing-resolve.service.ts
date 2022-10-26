import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IInterfacing } from '../interfacing.model';
import { InterfacingService } from '../service/interfacing.service';

@Injectable({ providedIn: 'root' })
export class InterfacingRoutingResolveService implements Resolve<IInterfacing | null> {
  constructor(protected service: InterfacingService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInterfacing | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((interfacing: HttpResponse<IInterfacing>) => {
          if (interfacing.body) {
            return of(interfacing.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
