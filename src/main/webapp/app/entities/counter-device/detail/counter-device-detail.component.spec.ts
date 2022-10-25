import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CounterDeviceDetailComponent } from './counter-device-detail.component';

describe('CounterDevice Management Detail Component', () => {
  let comp: CounterDeviceDetailComponent;
  let fixture: ComponentFixture<CounterDeviceDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CounterDeviceDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ counterDevice: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CounterDeviceDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CounterDeviceDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load counterDevice on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.counterDevice).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
