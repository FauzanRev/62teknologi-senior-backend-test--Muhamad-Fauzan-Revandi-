package com.teknologi.senior.backend.test.model.dto.request;

import lombok.Data;

@Data
public class RequestBody {
    private String term;
    private String categories;
    private Boolean openNow;
    private String countryCode;
    private String sortByRating;
}
