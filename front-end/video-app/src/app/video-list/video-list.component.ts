import { Component, OnInit } from '@angular/core';
import {VideoModel} from "../models/video.model";
import {VideoService} from "../service/video.service";
import {HttpErrorResponse} from "@angular/common/http";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {VideoUploadModalComponent} from "../video-upload-modal/video-upload-modal.component";

@Component({
  selector: 'app-video-list',
  templateUrl: './video-list.component.html',
  styleUrls: ['./video-list.component.scss']
})
export class VideoListComponent implements OnInit {

  videos: VideoModel[] = []

  constructor(private videoService: VideoService,
              private modalService: NgbModal) { }

  ngOnInit(): void {
    this.getVideos()
  }

  getVideos(): void {
    this.videoService.getVideo().then((videos: VideoModel[]) => {
      this.videos = videos
      console.log(this.videos)
    }, (err: HttpErrorResponse) => {
      console.log('err')
      console.log(err)
    })
  }

  openAddModal(): void {
    const modal = this.modalService.open(
      VideoUploadModalComponent,
      {size: 'lg'}
    )
  }
}
