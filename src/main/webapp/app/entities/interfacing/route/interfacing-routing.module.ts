import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { InterfacingComponent } from '../list/interfacing.component';
import { InterfacingDetailComponent } from '../detail/interfacing-detail.component';
import { InterfacingUpdateComponent } from '../update/interfacing-update.component';
import { InterfacingRoutingResolveService } from './interfacing-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const interfacingRoute: Routes = [
  {
    path: '',
    component: InterfacingComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InterfacingDetailComponent,
    resolve: {
      interfacing: InterfacingRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InterfacingUpdateComponent,
    resolve: {
      interfacing: InterfacingRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InterfacingUpdateComponent,
    resolve: {
      interfacing: InterfacingRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(interfacingRoute)],
  exports: [RouterModule],
})
export class InterfacingRoutingModule {}
