package com.company.waseem_20210814.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.company.waseem_20210814.dto.VideoThumbnailDto;
import com.company.waseem_20210814.entity.VideoThumbnail;
import com.company.waseem_20210814.repository.VideoThumbnailRepository;

@Service
public class VideoThumbnailService {

    private VideoThumbnailRepository videoThumbnailRepository;

    public VideoThumbnailService(final VideoThumbnailRepository videoThumbnailRepository) {
        this.videoThumbnailRepository = videoThumbnailRepository;
    }

    public void save(List<VideoThumbnailDto> thumbnailDtoList, Integer videoId) {
        var thumbnails =
        thumbnailDtoList.stream()
                .map(thumbnail -> new VideoThumbnail(thumbnail.getFilePath(), thumbnail.getSize(), videoId))
                .collect(Collectors.toList());

        videoThumbnailRepository.saveAll(thumbnails);
    }
}
