import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMyths } from './all-myths';

describe('AllMyths', () => {
  let component: AllMyths;
  let fixture: ComponentFixture<AllMyths>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllMyths]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllMyths);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
