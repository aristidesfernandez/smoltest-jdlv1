import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { IslandComponent } from '../list/island.component';
import { IslandDetailComponent } from '../detail/island-detail.component';
import { IslandUpdateComponent } from '../update/island-update.component';
import { IslandRoutingResolveService } from './island-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const islandRoute: Routes = [
  {
    path: '',
    component: IslandComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IslandDetailComponent,
    resolve: {
      island: IslandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IslandUpdateComponent,
    resolve: {
      island: IslandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IslandUpdateComponent,
    resolve: {
      island: IslandRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(islandRoute)],
  exports: [RouterModule],
})
export class IslandRoutingModule {}
