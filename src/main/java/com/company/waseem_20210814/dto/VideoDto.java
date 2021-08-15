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
                //ideally localhost path should not be append here but I am limited to use local file storage otherwise solution can be more
                // elegant using proper CDN
                        .map(thumbnail -> new VideoThumbnailDto( "http://localhost:8080/file" + thumbnail.getFilePath(), thumbnail.getSize()))
                        .collect(Collectors.toList());
    }
}
