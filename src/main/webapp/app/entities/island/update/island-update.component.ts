import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IslandFormService, IslandFormGroup } from './island-form.service';
import { IIsland } from '../island.model';
import { IslandService } from '../service/island.service';

@Component({
  selector: 'jhi-island-update',
  templateUrl: './island-update.component.html',
})
export class IslandUpdateComponent implements OnInit {
  isSaving = false;
  island: IIsland | null = null;

  editForm: IslandFormGroup = this.islandFormService.createIslandFormGroup();

  constructor(
    protected islandService: IslandService,
    protected islandFormService: IslandFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ island }) => {
      this.island = island;
      if (island) {
        this.updateForm(island);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const island = this.islandFormService.getIsland(this.editForm);
    if (island.id !== null) {
      this.subscribeToSaveResponse(this.islandService.update(island));
    } else {
      this.subscribeToSaveResponse(this.islandService.create(island));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIsland>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(island: IIsland): void {
    this.island = island;
    this.islandFormService.resetForm(this.editForm, island);
  }
}
