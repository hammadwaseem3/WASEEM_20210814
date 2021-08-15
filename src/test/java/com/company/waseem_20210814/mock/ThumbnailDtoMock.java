package com.company.waseem_20210814.mock;

import java.util.Arrays;
import java.util.List;

import com.company.waseem_20210814.dto.VideoThumbnailDto;

public class ThumbnailDtoMock {

    public static List<VideoThumbnailDto> getThumbnailDtoListMock() {
        return Arrays.asList(
                new VideoThumbnailDto("path 1", 64),
                new VideoThumbnailDto("path 2", 128),
                new VideoThumbnailDto("path 3", 256)
        );
    }
}
