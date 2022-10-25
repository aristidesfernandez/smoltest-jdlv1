import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIsland } from '../island.model';

@Component({
  selector: 'jhi-island-detail',
  templateUrl: './island-detail.component.html',
})
export class IslandDetailComponent implements OnInit {
  island: IIsland | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ island }) => {
      this.island = island;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
