import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { IslandComponent } from './list/island.component';
import { IslandDetailComponent } from './detail/island-detail.component';
import { IslandUpdateComponent } from './update/island-update.component';
import { IslandDeleteDialogComponent } from './delete/island-delete-dialog.component';
import { IslandRoutingModule } from './route/island-routing.module';

@NgModule({
  imports: [SharedModule, IslandRoutingModule],
  declarations: [IslandComponent, IslandDetailComponent, IslandUpdateComponent, IslandDeleteDialogComponent],
})
export class IslandModule {}
