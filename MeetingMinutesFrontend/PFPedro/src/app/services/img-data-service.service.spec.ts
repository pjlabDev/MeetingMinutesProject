import { TestBed } from '@angular/core/testing';

import { ImgDataServiceService } from './img-data-service.service';

describe('ImgDataServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ImgDataServiceService = TestBed.get(ImgDataServiceService);
    expect(service).toBeTruthy();
  });
});
