import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifSerieReunionComponent } from './modif-serie-reunion.component';

describe('ModifSerieReunionComponent', () => {
  let component: ModifSerieReunionComponent;
  let fixture: ComponentFixture<ModifSerieReunionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifSerieReunionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifSerieReunionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
