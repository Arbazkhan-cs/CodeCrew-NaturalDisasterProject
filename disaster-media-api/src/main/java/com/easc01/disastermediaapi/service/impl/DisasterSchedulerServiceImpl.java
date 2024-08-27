package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.disaster.ProcessedDisasterData;
import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchList;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisasterSchedulerServiceImpl implements DisasterSchedulerService {

    private final YouTubeService youTubeService;
    private final GenerativeAIService generativeAIService;
    private final DisasterRepository disasterRepository;

    @Override
    public double collectAndSaveDisastersFromYouTube() {
        long startTime = System.currentTimeMillis();

        try {
            // Step 1: Collection
            YouTubeSearchList youtubeDisasterData = youTubeService.fetchRecentNaturalDisastersPosts();
            // Sub step 1: Serialize result
            List<RawDisasterData> rawDisasterData = serializeYouTubeResults(youtubeDisasterData.getItems());
            log.info("Number of raw disasters scrapped: {}", rawDisasterData.size());

            log.info("Processing Raw Disaster Data using AI...");
            // Step 2: AI processing
            List<AIProcessedDisaster> aiProcessedData = generativeAIService.processRawDisasterData(rawDisasterData);
            log.info("Number of raw disasters processed using AI: {}", aiProcessedData.size());

            log.info("Mapping AI processed Data back to Original Disaster Data...");
            // Sub step 2: Map AI processed data with original data
            List<ProcessedDisasterData> mappedDisasterData = mapAIProcessedDataWithOriginal(youtubeDisasterData.getItems(), aiProcessedData);
            log.info("Number of processed mapped disasters remains: {}", mappedDisasterData.size());

            log.info("Saving final Mapped Data...");
            // Step 3: Save mapped and processed disasters
            boolean success = saveProcessAndMappedDisasters(mappedDisasterData);
            log.info("Status of saving last 20 minutes of disasters from YouTube: {}", success);

        } catch (Exception e) {
            log.error("Something went wrong collecting disasters from YouTube: {}", e.getMessage());
            throw e;
        }

        return (double) (System.currentTimeMillis() - startTime) / 1000;
    }

    private List<RawDisasterData> serializeYouTubeResults(List<YouTubeSearchList.SearchResult> disasterData) {
        return disasterData.parallelStream()
                .map(disaster -> RawDisasterData.builder()
                        .id(disaster.getId().getVideoId())
                        .title(disaster.getSnippet().getTitle())
                        .description(disaster.getSnippet().getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ProcessedDisasterData> mapAIProcessedDataWithOriginal(
            List<YouTubeSearchList.SearchResult> youtubeDisasterData,
            List<AIProcessedDisaster> aiProcessedData
    ) {
        return youtubeDisasterData.parallelStream()
                .map(disasterItem -> {
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
                                        .thumbnail(disasterItem.getSnippet().getThumbnails().getMedium().getUrl())
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
                .collect(Collectors.toList());
    }

    private boolean saveProcessAndMappedDisasters(List<ProcessedDisasterData> mappedDisasterData) {
        try {
            // Get all existing records by their recordIds
            Set<String> recordIds = mappedDisasterData.stream()
                    .map(ProcessedDisasterData::getRecordId)
                    .collect(Collectors.toSet());

            List<Disaster> existingDisasters = disasterRepository.findAllByRecordIdIn(recordIds);
            Map<String, Disaster> disasterMap = existingDisasters.stream()
                    .collect(Collectors.toMap(Disaster::getRecordId, disaster -> disaster));

            List<Disaster> disastersToSave = mappedDisasterData.stream()
                    .map(mappedDisaster -> serializeOrUpdateDisaster(mappedDisaster, disasterMap))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            disasterRepository.saveAll(disastersToSave);
            return true;

        } catch (Exception e) {
            log.error("Error in saving set of new disasters", e);
            return false;
        }
    }

    private Disaster serializeOrUpdateDisaster(ProcessedDisasterData mappedDisaster, Map<String, Disaster> existingDisasters) {
        try {
            log.info("Processing record id {} : ", mappedDisaster.getRecordId());

            Disaster disaster = existingDisasters.get(mappedDisaster.getRecordId());

            if (disaster == null) {
                disaster = Disaster.builder()
                        .recordId(mappedDisaster.getRecordId())
                        .title(mappedDisaster.getTitle())
                        .summary(mappedDisaster.getSummary())
                        .incidentLocation(mappedDisaster.getIncidentLocation())
                        .incidentType(mappedDisaster.getIncidentType())
                        .build();
                disaster.setVideos(mappedDisaster.getVideos());

            } else {
                disaster.setSummary(mappedDisaster.getSummary() + "\n\n" +  disaster.getSummary());
                disaster.setIncidentLocation(mappedDisaster.getIncidentLocation());
                disaster.setIncidentType(mappedDisaster.getIncidentType());
                disaster.getVideos().addAll(mappedDisaster.getVideos());
            }

            // Set disaster reference for videos
            Disaster finalDisaster = disaster;
            mappedDisaster.getVideos().forEach(video -> video.setDisaster(finalDisaster));

            return disaster;

        } catch (Exception e) {
            log.error("Error serializing disaster of Id {} for saving", mappedDisaster.getId(), e);
            return null;
        }
    }
}
