package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.ProcessedDisasterData;
import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.model.Video;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.DisasterSchedulerService;
import com.easc01.disastermediaapi.service.GenerativeAIService;
import com.easc01.disastermediaapi.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterSchedulerServiceImpl implements DisasterSchedulerService {

    private final YouTubeService youTubeService;
    private final GenerativeAIService generativeAIService;

    private final DisasterRepository disasterRepository;

    @Override
    public void collectAndSaveDisastersFromYouTube() {
        try {
            // Step 1: collection
            YouTubeSearchListResponseDTO youtubeDisasterData = youTubeService.fetchRecentNaturalDisastersPosts();
            // sub step 1: serialize result
            List<RawDisasterData> rawDisasterData = serializeYouTubeResults(youtubeDisasterData.getItems());

            // Step 2: AI procession
            List<AIProcessedDisaster> aiProcessedData = generativeAIService.processRawDisasterData(rawDisasterData);
            // sub step 2: map AI processed data with original data
            List<ProcessedDisasterData> mappedDisasterData = mapAIProcessedDataWithOriginal(youtubeDisasterData.getItems(), aiProcessedData);

            // Step 3: Save mapped and processed disasters
            boolean success = saveProcessAndMappedDisasters(mappedDisasterData);
            log.info("Status of saving last 15 minutes of disasters from youtube" + success);

        } catch (Exception e) {
            log.info("Something went wrong collecting disasters from youtube, {}", e.getMessage());
            throw e;

        }

    }

    private List<RawDisasterData> serializeYouTubeResults(List<YouTubeSearchListResponseDTO.SearchResult> disasterData) {
        return disasterData.parallelStream()
                .map((disaster) -> RawDisasterData.builder()
                        .id(disaster.getId().getVideoId())
                        .title(disaster.getSnippet().getTitle())
                        .description(disaster.getSnippet().getDescription())
                        .build())
                .toList();
    }

    private List<ProcessedDisasterData> mapAIProcessedDataWithOriginal(
            List<YouTubeSearchListResponseDTO.SearchResult> youtubeDisasterData,
            List<AIProcessedDisaster> aiProcessedData
    ) {
        return youtubeDisasterData.parallelStream()
                .map((disasterItem) -> {
                    Optional<AIProcessedDisaster> currentAIProcessedData = aiProcessedData.stream()
                            .filter(data -> Objects.equals(data.getId(), disasterItem.getId().getVideoId()))
                            .findFirst();

                    if (currentAIProcessedData.isEmpty()) {
                        return null;

                    } else {
                        AIProcessedDisaster aiProcessedDisaster = currentAIProcessedData.get();
                        Set<Video> videos = Set.of(
                                Video.builder()
                                        .title(disasterItem.getSnippet().getTitle())
                                        .userId(disasterItem.getSnippet().getChannelId())
                                        .url("https://www.youtube.com/watch?v=" + disasterItem.getId().getVideoId())
                                        .description(disasterItem.getSnippet().getDescription())
                                        .publishedDate(Instant.parse(disasterItem.getSnippet().getPublishedAt()))
                                        .build()
                        );

                        return ProcessedDisasterData.builder()
                                .id(disasterItem.getId().getVideoId())
                                .recordId(aiProcessedDisaster.getDisasterId())
                                .title(aiProcessedDisaster.getTitle())
                                .summary(aiProcessedDisaster.getSummary())
                                .incidentLocation(aiProcessedDisaster.getLocation())
                                .incidentType(aiProcessedDisaster.getIncidentType())
                                .videos(videos)
                                .build();
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }


    private boolean saveProcessAndMappedDisasters(List<ProcessedDisasterData> mappedDisasterData) {
        try {
            List<Disaster> disastersToSave = mappedDisasterData.parallelStream()
                    .map(this::serializeDuplicateDisaster)
                    .filter(Objects::nonNull)
                    .toList();

            disasterRepository.saveAll(disastersToSave);
            return true;

        } catch (Exception e) {
            log.error("Error in saving set of new disasters");
            return false;
        }
    }

    private Disaster serializeDuplicateDisaster(ProcessedDisasterData mappedDisaster) {
        try {
            Optional<Disaster> similarDisaster = disasterRepository.findByRecordId(mappedDisaster.getRecordId());

            if (similarDisaster.isEmpty()) {
                return Disaster.builder()
                        .recordId(mappedDisaster.getRecordId())
                        .title(mappedDisaster.getTitle())
                        .summary(mappedDisaster.getSummary())
                        .incidentLocation(mappedDisaster.getIncidentLocation())
                        .incidentLocation(mappedDisaster.getIncidentLocation())
                        .videos(mappedDisaster.getVideos())
                        .build();

            } else {
                similarDisaster.get().getVideos().addAll(mappedDisaster.getVideos());
                return similarDisaster.get();

            }

        } catch (Exception e) {
            log.error("Error serializing disaster of Id {} for saving", mappedDisaster.getId());
            return null;
        }
    }


}
