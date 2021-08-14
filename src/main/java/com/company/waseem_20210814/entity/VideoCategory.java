package com.company.waseem_20210814.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class  VideoCategory {

    @Id
    private Integer id;

    private String name;

}
