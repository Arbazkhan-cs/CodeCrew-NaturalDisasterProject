package com.easc01.disastermediaapi.dto.disaster;

import com.easc01.disastermediaapi.model.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class ProcessedDisasterData {
    private String id;
    private String recordId;
    private String title;
    private String summary;
    private String incidentLocation;
    private String incidentType;
    private Set<Video> videos;
}