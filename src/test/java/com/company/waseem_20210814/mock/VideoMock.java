package com.company.waseem_20210814.mock;

import static com.company.waseem_20210814.mock.VideoCategoryMock.getVideoCategoryMock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.company.waseem_20210814.entity.Video;

public class VideoMock {

    public static List<Video> getVideoMock() {
        var category = getVideoCategoryMock().get(0);
        return Arrays.asList(
                new Video(1, "tile 1", category, "file path 1", Collections.emptyList()),
                new Video(2, "title 2", category, "file path 2", Collections.emptyList())
        );
    }
}
