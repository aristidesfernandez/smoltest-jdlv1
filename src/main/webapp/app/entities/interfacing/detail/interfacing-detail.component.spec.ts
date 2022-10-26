import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InterfacingDetailComponent } from './interfacing-detail.component';

describe('Interfacing Management Detail Component', () => {
  let comp: InterfacingDetailComponent;
  let fixture: ComponentFixture<InterfacingDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InterfacingDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ interfacing: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(InterfacingDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(InterfacingDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load interfacing on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.interfacing).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
