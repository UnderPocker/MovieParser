package com.akr.course.movieparser.site_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SearchResponse {
    @JsonProperty("query_response")
    private QueryResponse queryResponse;
}
