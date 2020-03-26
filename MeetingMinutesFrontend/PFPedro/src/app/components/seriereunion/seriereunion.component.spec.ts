import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeriereunionComponent } from './seriereunion.component';

describe('SeriereunionComponent', () => {
  let component: SeriereunionComponent;
  let fixture: ComponentFixture<SeriereunionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeriereunionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeriereunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
