export class VideoModel {
  id: number | undefined
  thumbnails: Array<ThumbnailModel> = [];
  title: string | undefined
}

export class ThumbnailModel {
  filePath: string | undefined
  size: number | undefined
}
