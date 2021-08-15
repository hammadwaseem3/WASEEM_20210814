package com.company.waseem_20210814.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private VideoCategory category;

    private String filePath;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "video",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<VideoThumbnail> thumbnails;

    public Video(final Integer id) {
        this.id = id;
    }

    public Video(final String title, final VideoCategory category, final String filePath) {
        this.title = title;
        this.category = category;
        this.filePath = filePath;
    }
}
