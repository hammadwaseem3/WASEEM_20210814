import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {VideoModel} from "../models/video.model";
import {CategoryModel} from "../models/category.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseUrl: string

  constructor(private http: HttpClient) {
    this.baseUrl = `${environment.baseUrl}/video/category`
  }

  getAllCategory(): Promise<CategoryModel[]> {
    const url = `${this.baseUrl}`
    return this.http.get<CategoryModel[]>(url).toPromise()
  }
}
