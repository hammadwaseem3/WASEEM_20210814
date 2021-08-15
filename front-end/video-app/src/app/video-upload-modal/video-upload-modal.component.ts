import { Component, OnInit } from '@angular/core';
import {VideoService} from "../service/video.service";
import {CategoryService} from "../service/category.service";
import {CategoryModel} from "../models/category.model";

@Component({
  selector: 'app-video-upload-modal',
  templateUrl: './video-upload-modal.component.html',
  styleUrls: ['./video-upload-modal.component.scss']
})
export class VideoUploadModalComponent implements OnInit {

  title: string = "";
  categoryId: number  | undefined = 1;
  fileToUpload: File | any;
  categoryModelList: CategoryModel[] = []

  constructor(private videoService: VideoService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getAllCategory().then((categories: CategoryModel[]) => {
      this.categoryModelList = categories
    })
  }

  handleFileInput(event: Event): void {
    // @ts-ignore
    if (event.target.files instanceof FileList) {
      // @ts-ignore
      this.fileToUpload = event.target.files[0]
    }
    console.log(this.fileToUpload)
  }

  onUpload(): void {
    if (!this.fileToUpload || this.fileToUpload.name === '') {
      return
    }



  }

  onCategoryId(id: number | undefined) {
    this.categoryId = id
  }
}
