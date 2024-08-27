package com.easc01.disastermediaapi.dto.generativeai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class AIProcessedDisaster {
    @JsonProperty("id_")
    private String id;

    @JsonProperty("text")
    private String title;

    @JsonProperty("description")
    private String summary;

    @JsonProperty("location")
    private String location;

    @JsonProperty("incidentType")
    private String incidentType;

    @JsonProperty("recordid")
    private String disasterId;
}
