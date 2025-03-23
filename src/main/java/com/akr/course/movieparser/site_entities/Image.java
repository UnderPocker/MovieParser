package com.akr.course.movieparser.site_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Image {
    @JsonProperty("medium_resolution_url")
    private String url;
}
