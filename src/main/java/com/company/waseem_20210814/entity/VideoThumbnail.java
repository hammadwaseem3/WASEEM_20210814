package com.company.waseem_20210814.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class VideoThumbnail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String filePath;

    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Video video;

    public VideoThumbnail(final String filePath, final Integer size, final Integer videoId) {
        this.filePath = filePath;
        this.size = size;
        this.video = new Video(videoId);
    }
}
