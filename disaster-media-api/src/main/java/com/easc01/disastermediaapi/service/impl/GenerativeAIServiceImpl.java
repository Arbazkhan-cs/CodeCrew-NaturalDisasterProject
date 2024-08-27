package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.service.GenerativeAIService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerativeAIServiceImpl implements GenerativeAIService {

    private final ObjectMapper objectMapper;

    private static final String url = "https://arbazkhan-cs-disaster-news-summary.hf.space/summarize";

    private final OkHttpClient client = new OkHttpClient();

    {
        client.setConnectTimeout(10, TimeUnit.MINUTES);  // Set connection timeout
        client.setReadTimeout(10, TimeUnit.MINUTES);     // Set read timeout
    }


    @Override
    public List<AIProcessedDisaster> processRawDisasterData(List<RawDisasterData> rawDisasterData) {
        try {
            String jsonPayload = objectMapper.writeValueAsString(rawDisasterData);
            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json"), jsonPayload);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JavaType listType = objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, AIProcessedDisaster.class);

                List<AIProcessedDisaster> aiProcessedDisasters = objectMapper.readValue(responseBody, listType);
                List<AIProcessedDisaster> filterDisasters = aiProcessedDisasters.stream()
                        .filter((data) -> data.getId() != null && data.getSummary() != null && data.getTitle() != null && data.getDisasterId() != null)
                        .toList();

                log.info("AI Processed Disasters -:");
                for (AIProcessedDisaster disaster : filterDisasters) {
                    log.info(disaster.toString());
                }

                return filterDisasters;

            } else {
                log.error("Request failed with status code: {}", response.code());
                throw new RuntimeException("Failed to process data using AI");
            }
        } catch (IOException e) {
            log.error("Failed to process data using AI, {}", e.getMessage());
            throw new RuntimeException("Failed to process data using AI", e);
        }
    }
}
