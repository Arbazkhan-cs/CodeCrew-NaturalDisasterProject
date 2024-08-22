package com.easc01.disastermediaapi.dto.generativeai;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RawDisasterData {
    private String id;
    private String title;
    private String description;

}
