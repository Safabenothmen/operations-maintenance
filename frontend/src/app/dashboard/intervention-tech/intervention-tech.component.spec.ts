import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterventionTechComponent } from './intervention-tech.component';

describe('InterventionTechComponent', () => {
  let component: InterventionTechComponent;
  let fixture: ComponentFixture<InterventionTechComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InterventionTechComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterventionTechComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
