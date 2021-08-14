package com.company.waseem_20210814.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.waseem_20210814.entity.VideoCategory;
import com.company.waseem_20210814.repository.VideoCategoryRepository;

@Service
public class VideoCategoryService {

    private VideoCategoryRepository videoCategoryRepository;

    public VideoCategoryService(final VideoCategoryRepository videoCategoryRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
    }

    public List<VideoCategory> findAll() {
        return videoCategoryRepository.findAll();
    }
}
