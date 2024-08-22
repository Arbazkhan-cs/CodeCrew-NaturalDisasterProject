package com.easc01.disastermediaapi.dto.disaster;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class VideoData {
    private String title;
    private String description;
    private String url;
    private Instant publishedDate;
}
