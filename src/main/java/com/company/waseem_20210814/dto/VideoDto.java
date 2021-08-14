package com.company.waseem_20210814.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.company.waseem_20210814.entity.VideoThumbnail;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;

@JsonAutoDetect
@Getter
public class VideoDto {

    private Integer id;
    private String title;
    private String category;
    private List<VideoThumbnailDto> thumbnails;

    public VideoDto(final Integer id, final String title, final String category, final List<VideoThumbnail> thumbnails) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.thumbnails = thumbnails.stream()
                        .map(thumbnail -> new VideoThumbnailDto(thumbnail.getFilePath(), thumbnail.getSize()))
                        .collect(Collectors.toList());
    }
}
