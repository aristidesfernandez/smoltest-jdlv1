import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { MunicipalityFormService, MunicipalityFormGroup } from './municipality-form.service';
import { IMunicipality } from '../municipality.model';
import { MunicipalityService } from '../service/municipality.service';
import { IDepartment } from 'app/entities/department/department.model';
import { DepartmentService } from 'app/entities/department/service/department.service';

@Component({
  selector: 'jhi-municipality-update',
  templateUrl: './municipality-update.component.html',
})
export class MunicipalityUpdateComponent implements OnInit {
  isSaving = false;
  municipality: IMunicipality | null = null;

  departmentsSharedCollection: IDepartment[] = [];

  editForm: MunicipalityFormGroup = this.municipalityFormService.createMunicipalityFormGroup();

  constructor(
    protected municipalityService: MunicipalityService,
    protected municipalityFormService: MunicipalityFormService,
    protected departmentService: DepartmentService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareDepartment = (o1: IDepartment | null, o2: IDepartment | null): boolean => this.departmentService.compareDepartment(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ municipality }) => {
      this.municipality = municipality;
      if (municipality) {
        this.updateForm(municipality);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const municipality = this.municipalityFormService.getMunicipality(this.editForm);
    if (municipality.id !== null) {
      this.subscribeToSaveResponse(this.municipalityService.update(municipality));
    } else {
      this.subscribeToSaveResponse(this.municipalityService.create(municipality));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMunicipality>>): void {
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

  protected updateForm(municipality: IMunicipality): void {
    this.municipality = municipality;
    this.municipalityFormService.resetForm(this.editForm, municipality);

    this.departmentsSharedCollection = this.departmentService.addDepartmentToCollectionIfMissing<IDepartment>(
      this.departmentsSharedCollection,
      municipality.department
    );
  }

  protected loadRelationshipsOptions(): void {
    this.departmentService
      .query()
      .pipe(map((res: HttpResponse<IDepartment[]>) => res.body ?? []))
      .pipe(
        map((departments: IDepartment[]) =>
          this.departmentService.addDepartmentToCollectionIfMissing<IDepartment>(departments, this.municipality?.department)
        )
      )
      .subscribe((departments: IDepartment[]) => (this.departmentsSharedCollection = departments));
  }
}
