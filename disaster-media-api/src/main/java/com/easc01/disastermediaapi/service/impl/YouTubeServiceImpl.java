package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {

    private final Environment env;
    private final ObjectMapper objectMapper;

    @Override
    public YouTubeSearchListResponseDTO fetchRecentNaturalDisastersPosts() {
        String apiKey = env.getProperty("youtube.api.key");
        String publishedAfter = TimeUtil.instantToStringV1(Instant.now().minus(15, ChronoUnit.MINUTES));

        String searchQuery = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/search")
                .queryParam("q", "#earthquake | #tsunami | #flood | #naturaldisaster | #storm | #volcano | #cyclone | #landslide")
                .queryParam("key", apiKey)
                .queryParam("type", "video")
                .queryParam("safeSearch", "strict")
                .queryParam("part", "snippet")
                .queryParam("maxResults", "50")
                .queryParam("videoCategoryId", "25")
                .queryParam("publishedAfter", publishedAfter)
                .toUriString();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(searchQuery);
            CloseableHttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return objectMapper.readValue(responseBody, YouTubeSearchListResponseDTO.class);

            } else {
                log.error("Request failed with status code: {}", response.getStatusLine().getStatusCode());
                throw new RuntimeException("Failed to fetch data from YouTube API");
            }

        } catch (IOException e) {
            log.error("Failed to receive YouTube Disaster Data, {}", e.getMessage());
            throw new RuntimeException("Failed to receive YouTube Disaster Data", e);
        }
    }
}
