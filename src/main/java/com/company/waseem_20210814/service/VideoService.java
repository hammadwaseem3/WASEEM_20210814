package com.company.waseem_20210814.service;

import java.io.IOException;
import java.util.List;


import org.springframework.stereotype.Service;

import com.company.waseem_20210814.entity.Video;
import com.company.waseem_20210814.entity.VideoCategory;
import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.repository.VideoRepository;

@Service
public class VideoService {

    private VideoRepository videoRepository;
    private ThumbnailService thumbnailService;

    public VideoService(final VideoRepository videoRepository, final ThumbnailService thumbnailService) throws IOException {
        this.videoRepository = videoRepository;
        this.thumbnailService = thumbnailService;
    }

    public boolean save(final String title, final VideoCategory category, final String filePath) {
        try {
            var video = new Video(title, category, filePath);
            video = videoRepository.save(video);
            thumbnailService.createThumbnailAsync(filePath, video.getId());
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Video findById(final Integer id) throws EntityNotFoundException {
        var optionalVideo = videoRepository.findById(id);
        if (optionalVideo.isEmpty()) {
            throw new EntityNotFoundException("video not found");
        }
        return optionalVideo.get();
    }

    public List<Video> findAll() {
        return videoRepository.findAll();
    }
}
