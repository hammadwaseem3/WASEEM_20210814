import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {VideoModel} from "../models/video.model";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  baseUrl: string

  constructor(private http: HttpClient) {
    this.baseUrl = `${environment.baseUrl}/video`
  }

  getVideo(): Promise<VideoModel[]> {
    const url = `${this.baseUrl}/all`
    return this.http.get<VideoModel[]>(url).toPromise()
  }
}
