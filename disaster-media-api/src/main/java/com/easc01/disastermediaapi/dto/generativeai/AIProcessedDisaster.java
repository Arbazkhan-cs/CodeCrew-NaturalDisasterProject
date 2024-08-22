package com.easc01.disastermediaapi.dto.generativeai;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AIProcessedDisaster {
    private String id;
    private String title;
    private String summary;
    private String location;
    private String incidentType;
    private String verified;
    private String disasterId;
}
