package com.company.waseem_20210814.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonAutoDetect
@Getter
public class VideoCategoryDto {

    private Integer id;
    private String name;

}
