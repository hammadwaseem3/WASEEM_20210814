import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideoUploadModalComponent } from './video-upload-modal.component';

describe('VideoUploadModalComponent', () => {
  let component: VideoUploadModalComponent;
  let fixture: ComponentFixture<VideoUploadModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VideoUploadModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VideoUploadModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
