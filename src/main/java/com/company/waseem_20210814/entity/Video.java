package com.company.waseem_20210814.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Video {

    @Id
    private Integer id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private VideoCategory category;

    private String filePath;


}
