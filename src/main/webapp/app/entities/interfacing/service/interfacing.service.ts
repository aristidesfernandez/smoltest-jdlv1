import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IInterfacing, NewInterfacing } from '../interfacing.model';

export type PartialUpdateInterfacing = Partial<IInterfacing> & Pick<IInterfacing, 'id'>;

export type EntityResponseType = HttpResponse<IInterfacing>;
export type EntityArrayResponseType = HttpResponse<IInterfacing[]>;

@Injectable({ providedIn: 'root' })
export class InterfacingService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/interfacings');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(interfacing: NewInterfacing): Observable<EntityResponseType> {
    return this.http.post<IInterfacing>(this.resourceUrl, interfacing, { observe: 'response' });
  }

  update(interfacing: IInterfacing): Observable<EntityResponseType> {
    return this.http.put<IInterfacing>(`${this.resourceUrl}/${this.getInterfacingIdentifier(interfacing)}`, interfacing, {
      observe: 'response',
    });
  }

  partialUpdate(interfacing: PartialUpdateInterfacing): Observable<EntityResponseType> {
    return this.http.patch<IInterfacing>(`${this.resourceUrl}/${this.getInterfacingIdentifier(interfacing)}`, interfacing, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInterfacing>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInterfacing[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getInterfacingIdentifier(interfacing: Pick<IInterfacing, 'id'>): number {
    return interfacing.id;
  }

  compareInterfacing(o1: Pick<IInterfacing, 'id'> | null, o2: Pick<IInterfacing, 'id'> | null): boolean {
    return o1 && o2 ? this.getInterfacingIdentifier(o1) === this.getInterfacingIdentifier(o2) : o1 === o2;
  }

  addInterfacingToCollectionIfMissing<Type extends Pick<IInterfacing, 'id'>>(
    interfacingCollection: Type[],
    ...interfacingsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const interfacings: Type[] = interfacingsToCheck.filter(isPresent);
    if (interfacings.length > 0) {
      const interfacingCollectionIdentifiers = interfacingCollection.map(
        interfacingItem => this.getInterfacingIdentifier(interfacingItem)!
      );
      const interfacingsToAdd = interfacings.filter(interfacingItem => {
        const interfacingIdentifier = this.getInterfacingIdentifier(interfacingItem);
        if (interfacingCollectionIdentifiers.includes(interfacingIdentifier)) {
          return false;
        }
        interfacingCollectionIdentifiers.push(interfacingIdentifier);
        return true;
      });
      return [...interfacingsToAdd, ...interfacingCollection];
    }
    return interfacingCollection;
  }
}
