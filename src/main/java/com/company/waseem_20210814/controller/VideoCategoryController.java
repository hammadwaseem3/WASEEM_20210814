package com.company.waseem_20210814.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.waseem_20210814.dto.VideoCategoryDto;
import com.company.waseem_20210814.service.VideoCategoryService;

@RestController
@RequestMapping("video/category")
@CrossOrigin(origins = "*") //Its not safe to do because server is vulnerable but for the sake of test project I am doing it
public class VideoCategoryController {

    private VideoCategoryService videoCategoryService;

    public VideoCategoryController(final VideoCategoryService videoCategoryService) {
        this.videoCategoryService = videoCategoryService;
    }

    @GetMapping
    public List<VideoCategoryDto> getAllVideoCategories() {
        return
            videoCategoryService.findAll()
                .stream()
                .map(videoCategory -> new VideoCategoryDto(videoCategory.getId(), videoCategory.getName()))
                .collect(Collectors.toList());
    }
}
