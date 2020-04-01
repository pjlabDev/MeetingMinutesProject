import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevareunionComponent } from './nuevareunion.component';

describe('NuevareunionComponent', () => {
  let component: NuevareunionComponent;
  let fixture: ComponentFixture<NuevareunionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NuevareunionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevareunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
