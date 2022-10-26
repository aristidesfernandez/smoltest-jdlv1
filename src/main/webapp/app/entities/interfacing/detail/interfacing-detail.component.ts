import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInterfacing } from '../interfacing.model';

@Component({
  selector: 'jhi-interfacing-detail',
  templateUrl: './interfacing-detail.component.html',
})
export class InterfacingDetailComponent implements OnInit {
  interfacing: IInterfacing | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ interfacing }) => {
      this.interfacing = interfacing;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
