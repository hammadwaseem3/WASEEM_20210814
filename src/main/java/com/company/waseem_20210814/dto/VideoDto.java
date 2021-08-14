package com.company.waseem_20210814.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

@JsonAutoDetect
@AllArgsConstructor
public class VideoDto {

    private Integer id;
    private String title;
    private String category;

}
