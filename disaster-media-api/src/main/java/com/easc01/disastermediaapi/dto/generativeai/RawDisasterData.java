package com.easc01.disastermediaapi.dto.generativeai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RawDisasterData {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String title;

    @JsonProperty("description")
    private String description;

}
