import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevaseriereunionComponent } from './nuevaseriereunion.component';

describe('NuevaseriereunionComponent', () => {
  let component: NuevaseriereunionComponent;
  let fixture: ComponentFixture<NuevaseriereunionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NuevaseriereunionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevaseriereunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
