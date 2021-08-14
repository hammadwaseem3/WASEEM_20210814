package com.company.waseem_20210814.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private VideoCategory category;

    private String filePath;

    public Video(final String title, final VideoCategory category, final String filePath) {
        this.title = title;
        this.category = category;
        this.filePath = filePath;
    }
}
