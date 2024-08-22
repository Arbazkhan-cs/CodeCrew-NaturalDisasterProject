package com.easc01.disastermediaapi.dto.disaster;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class DisasterData {
    private String disasterId;
    private String title;
    private String summary;
    private List<VideoData> videos;
    private String incidentLocation;
    private String incidentType;
    private Date infoPublishedDate;
}