import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageexcersicerecommendationsComponent } from './manageexcersicerecommendations.component';

describe('ManageexcersicerecommendationsComponent', () => {
  let component: ManageexcersicerecommendationsComponent;
  let fixture: ComponentFixture<ManageexcersicerecommendationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageexcersicerecommendationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageexcersicerecommendationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
