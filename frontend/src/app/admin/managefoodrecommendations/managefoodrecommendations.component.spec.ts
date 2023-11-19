import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagefoodrecommendationsComponent } from './managefoodrecommendations.component';

describe('ManagefoodrecommendationsComponent', () => {
  let component: ManagefoodrecommendationsComponent;
  let fixture: ComponentFixture<ManagefoodrecommendationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagefoodrecommendationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManagefoodrecommendationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
