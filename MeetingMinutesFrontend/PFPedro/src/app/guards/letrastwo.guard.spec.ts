import { TestBed, async, inject } from '@angular/core/testing';

import { LetrastwoGuard } from './letrastwo.guard';

describe('LetrastwoGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LetrastwoGuard]
    });
  });

  it('should ...', inject([LetrastwoGuard], (guard: LetrastwoGuard) => {
    expect(guard).toBeTruthy();
  }));
});
