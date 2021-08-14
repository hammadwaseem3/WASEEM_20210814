package com.company.waseem_20210814.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoThumbnailDto {

    private Integer id;

    private String filePath;

    private Integer size;

    public VideoThumbnailDto(final String filePath, final Integer size) {
        this.filePath = filePath;
        this.size = size;
    }
}
