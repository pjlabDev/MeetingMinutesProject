import { TestBed, async, inject } from '@angular/core/testing';

import { LetrasGuard } from './letras.guard';

describe('LetrasGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LetrasGuard]
    });
  });

  it('should ...', inject([LetrasGuard], (guard: LetrasGuard) => {
    expect(guard).toBeTruthy();
  }));
});
