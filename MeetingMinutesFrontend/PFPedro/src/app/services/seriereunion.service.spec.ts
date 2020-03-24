import { TestBed } from '@angular/core/testing';

import { SeriereunionService } from './seriereunion.service';

describe('SeriereunionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SeriereunionService = TestBed.get(SeriereunionService);
    expect(service).toBeTruthy();
  });
});
