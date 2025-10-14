package com.saadeh.consultancy.SCMovie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}