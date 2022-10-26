import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IInterfacing } from '../interfacing.model';
import { InterfacingService } from '../service/interfacing.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './interfacing-delete-dialog.component.html',
})
export class InterfacingDeleteDialogComponent {
  interfacing?: IInterfacing;

  constructor(protected interfacingService: InterfacingService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.interfacingService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
