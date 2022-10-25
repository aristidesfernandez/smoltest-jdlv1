import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IIsland, NewIsland } from '../island.model';

export type PartialUpdateIsland = Partial<IIsland> & Pick<IIsland, 'id'>;

export type EntityResponseType = HttpResponse<IIsland>;
export type EntityArrayResponseType = HttpResponse<IIsland[]>;

@Injectable({ providedIn: 'root' })
export class IslandService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/islands');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(island: NewIsland): Observable<EntityResponseType> {
    return this.http.post<IIsland>(this.resourceUrl, island, { observe: 'response' });
  }

  update(island: IIsland): Observable<EntityResponseType> {
    return this.http.put<IIsland>(`${this.resourceUrl}/${this.getIslandIdentifier(island)}`, island, { observe: 'response' });
  }

  partialUpdate(island: PartialUpdateIsland): Observable<EntityResponseType> {
    return this.http.patch<IIsland>(`${this.resourceUrl}/${this.getIslandIdentifier(island)}`, island, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIsland>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIsland[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getIslandIdentifier(island: Pick<IIsland, 'id'>): number {
    return island.id;
  }

  compareIsland(o1: Pick<IIsland, 'id'> | null, o2: Pick<IIsland, 'id'> | null): boolean {
    return o1 && o2 ? this.getIslandIdentifier(o1) === this.getIslandIdentifier(o2) : o1 === o2;
  }

  addIslandToCollectionIfMissing<Type extends Pick<IIsland, 'id'>>(
    islandCollection: Type[],
    ...islandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const islands: Type[] = islandsToCheck.filter(isPresent);
    if (islands.length > 0) {
      const islandCollectionIdentifiers = islandCollection.map(islandItem => this.getIslandIdentifier(islandItem)!);
      const islandsToAdd = islands.filter(islandItem => {
        const islandIdentifier = this.getIslandIdentifier(islandItem);
        if (islandCollectionIdentifiers.includes(islandIdentifier)) {
          return false;
        }
        islandCollectionIdentifiers.push(islandIdentifier);
        return true;
      });
      return [...islandsToAdd, ...islandCollection];
    }
    return islandCollection;
  }
}
