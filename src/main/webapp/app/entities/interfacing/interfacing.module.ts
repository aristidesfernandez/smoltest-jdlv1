import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { InterfacingComponent } from './list/interfacing.component';
import { InterfacingDetailComponent } from './detail/interfacing-detail.component';
import { InterfacingUpdateComponent } from './update/interfacing-update.component';
import { InterfacingDeleteDialogComponent } from './delete/interfacing-delete-dialog.component';
import { InterfacingRoutingModule } from './route/interfacing-routing.module';

@NgModule({
  imports: [SharedModule, InterfacingRoutingModule],
  declarations: [InterfacingComponent, InterfacingDetailComponent, InterfacingUpdateComponent, InterfacingDeleteDialogComponent],
})
export class InterfacingModule {}
