package com.company.waseem_20210814.service;

import org.springframework.stereotype.Service;

import com.company.waseem_20210814.entity.Video;
import com.company.waseem_20210814.entity.VideoCategory;
import com.company.waseem_20210814.exception.EntityNotFoundException;
import com.company.waseem_20210814.repository.VideoRepository;

@Service
public class VideoService {

    private VideoRepository videoRepository;

    public VideoService(final VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void save(final String title, final VideoCategory category, final String filePath) {
        //TODO generate thumbnails
        var video = new Video(title, category, filePath);
        videoRepository.save(video);
    }

    public Video findById(final Integer id) throws EntityNotFoundException {
        var optionalVideo = videoRepository.findById(id);
        if (optionalVideo.isEmpty()) {
            throw new EntityNotFoundException("video not found");
        }
        return optionalVideo.get();
    }
}
