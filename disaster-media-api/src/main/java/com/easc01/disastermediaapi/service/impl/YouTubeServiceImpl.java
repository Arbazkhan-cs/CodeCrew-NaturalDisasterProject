package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchList;
import com.easc01.disastermediaapi.dto.youtube.YouTubeVideoData;
import com.easc01.disastermediaapi.service.YouTubeService;
import com.easc01.disastermediaapi.util.TimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {

    private final Environment env;
    private final ObjectMapper objectMapper;

    @Override
    public YouTubeSearchList fetchRecentNaturalDisastersPosts() {
        YouTubeSearchList allDisastersOverview = fetchRecentNaturalDisasters();
        log.info("YouTube videos fetched: {}", allDisastersOverview.getItems().size());

        List<YouTubeSearchList.SearchResult> detailedDisasters = allDisastersOverview.getItems().parallelStream()
                .map((videoOverview) -> {
                    YouTubeVideoData.SearchResult detailedVideoData = fetchVideoData(videoOverview.getId().getVideoId());

                    return YouTubeSearchList.SearchResult.builder()
                            .kind(detailedVideoData.getKind())
                            .etag(detailedVideoData.getEtag())
                            .id(YouTubeSearchList.SearchResult.VideoId.builder().videoId(detailedVideoData.getId()).build())
                            .snippet(YouTubeSearchList.SearchResult.Snippet.builder()
                                    .publishedAt(detailedVideoData.getSnippet().getPublishedAt())
                                    .channelId(detailedVideoData.getSnippet().getChannelId())
                                    .title(detailedVideoData.getSnippet().getTitle())
                                    .description(detailedVideoData.getSnippet().getDescription())
                                    .thumbnails(YouTubeSearchList.SearchResult.Snippet.Thumbnails.builder()
                                            .defaultThumbnail(detailedVideoData.getSnippet().getThumbnails().getDefaultThumbnail())
                                            .medium(detailedVideoData.getSnippet().getThumbnails().getMedium())
                                            .high(detailedVideoData.getSnippet().getThumbnails().getHigh())
                                            .standard(detailedVideoData.getSnippet().getThumbnails().getStandard())
                                            .maxres(detailedVideoData.getSnippet().getThumbnails().getMaxres())
                                            .build())
                                    .channelTitle(detailedVideoData.getSnippet().getChannelTitle())
                                    .tags(detailedVideoData.getSnippet().getTags())
                                    .categoryId(detailedVideoData.getSnippet().getCategoryId())
                                    .liveBroadcastContent(detailedVideoData.getSnippet().getLiveBroadcastContent())
                                    .defaultLanguage(detailedVideoData.getSnippet().getDefaultLanguage())
                                    .defaultAudioLanguage(detailedVideoData.getSnippet().getDefaultAudioLanguage())
                                    .build())
                            .build();

                })
//                 return only 'en' lang content
                .filter(data -> !data.getSnippet().getTitle().isBlank()
                        && !data.getSnippet().getDescription().isBlank())
                .toList();

        allDisastersOverview.setItems(detailedDisasters);
        log.info("YouTube videos remains after filters: {}", allDisastersOverview.getItems().size());
        return allDisastersOverview;
    }

    private YouTubeSearchList fetchRecentNaturalDisasters() {
        String apiKey = env.getProperty("youtube.api.key");
        String publishedAfter = TimeUtil.instantToStringV1(Instant.now().minus(20, ChronoUnit.MINUTES));

        String searchQuery = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/search")
                .queryParam("q", "#earthquake | #tsunami | #flood | #naturaldisaster | #storm | #volcano | #cyclone | #landslide | #drought | #wildfire | #avalanche | #hurricane | #tornado | #blizzard | #heatwave | #flashflood | #riverflood | #severeweather | #hailstorm | #mudslide")
                .queryParam("key", apiKey)
                .queryParam("type", "video")
                .queryParam("part", "snippet")
                .queryParam("relevanceLanguage", "en")
                .queryParam("safeSearch", "strict")
                .queryParam("maxResults", "15")
                .queryParam("videoCategoryId", "25")
                .queryParam("publishedAfter", publishedAfter)
                .toUriString();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(searchQuery);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    return objectMapper.readValue(responseBody, YouTubeSearchList.class);

                } else {
                    log.error("Request failed with status code: {}", response.getStatusLine().getStatusCode());
                    throw new RuntimeException("Failed to fetch data from YouTube API");
                }
            }

        } catch (IOException e) {
            log.error("Failed to receive YouTube Disaster Data, {}", e.getMessage());
            throw new RuntimeException("Failed to receive YouTube Disaster Data", e);
        }
    }


    private YouTubeVideoData.SearchResult fetchVideoData(String videoId) {
        String apiKey = env.getProperty("youtube.api.key");

        String searchQuery = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/videos")
                .queryParam("key", apiKey)
                .queryParam("id", videoId)
                .queryParam("part", "snippet")
                .toUriString();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(searchQuery);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    String responseBody = EntityUtils.toString(response.getEntity());
                    YouTubeVideoData youtubeResponseJson = objectMapper.readValue(responseBody, YouTubeVideoData.class);
                    return youtubeResponseJson.getItems().get(0);

                } else {
                    log.error("Request failed with status code: {}", response.getStatusLine().getStatusCode());
                    throw new RuntimeException("Failed to fetch video data from YouTube API");
                }
            }

        } catch (IOException e) {
            log.error("Failed to receive Video Data, {}", e.getMessage());
            throw new RuntimeException("Failed to receive Video Data", e);
        }
    }
}
