package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.DisasterData;
import com.easc01.disastermediaapi.dto.disaster.VideoData;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.DisasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterServiceImpl implements DisasterService {

    private final DisasterRepository disasterRepository;

    @Override
    public List<DisasterData> getProcessedDisasterDataByCriteria(
            String searchTag,
            String incidentType,
            String incidentLocation,
            String publishedBefore,
            String publishedAfter
    ) {
        List<Disaster> disasters = disasterRepository.findDisastersByCriteria(
                searchTag.toLowerCase(),
                incidentType.toLowerCase(),
                incidentLocation.toLowerCase(),
                Instant.parse(publishedAfter),
                Instant.parse(publishedBefore)
        );

        // Map disasters to response objects
        return disasters.parallelStream()
                .map(disaster -> DisasterData.builder()
                        .disasterId(disaster.getRecordId())
                        .title(disaster.getTitle())
                        .summary(disaster.getSummary())
                        .videos(disaster.getVideos().stream()
                                .map((video) -> VideoData.builder()
                                        .title(video.getTitle())
                                        .description(video.getDescription())
                                        .url(video.getUrl())
                                        .publishedDate(video.getPublishedDate())
                                        .build())
                                .toList())
                        .incidentLocation(disaster.getIncidentLocation())
                        .incidentType(disaster.getIncidentType())
                        .infoPublishedDate(Date.from(disaster.getUpdatedAt()))
                        .build())
                .toList();
    }




}
