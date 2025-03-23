package com.akr.course.movieparser.site_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueryResponse {
    @JsonProperty("number_of_results")
    private int numberOfResults;
    private Image[] images;

}
