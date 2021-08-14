package com.company.waseem_20210814.mock;

import java.util.Arrays;
import java.util.List;

import com.company.waseem_20210814.entity.VideoCategory;

public class VideoCategoryMock {

    public static List<VideoCategory> getVideoCategoryMock() {
        return Arrays.asList(
                new VideoCategory(1, "Exercise"),
                new VideoCategory(2, "Recipe")
        );
    }
}
