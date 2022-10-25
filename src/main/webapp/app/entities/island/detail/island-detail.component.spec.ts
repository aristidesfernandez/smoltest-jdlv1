import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { IslandDetailComponent } from './island-detail.component';

describe('Island Management Detail Component', () => {
  let comp: IslandDetailComponent;
  let fixture: ComponentFixture<IslandDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IslandDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ island: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(IslandDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(IslandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load island on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.island).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
