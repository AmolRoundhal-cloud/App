import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InfeedMissionRuntimeDetailsComponent } from './infeed-report.component';


describe('InfeedReportComponent', () => {
  let component: InfeedMissionRuntimeDetailsComponent;
  let fixture: ComponentFixture<InfeedMissionRuntimeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfeedMissionRuntimeDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfeedMissionRuntimeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
