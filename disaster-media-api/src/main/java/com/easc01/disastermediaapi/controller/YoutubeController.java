package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.dto.ApiResponse;
import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;
import com.easc01.disastermediaapi.service.YouTubeService;
import com.easc01.disastermediaapi.util.IDUtil;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping(path = AppConstant.YOUTUBE)
public class YoutubeController {

    private final YouTubeService youTubeService;

    @GetMapping(value = AppConstant.RECENT)
    public ResponseEntity<ApiResponse<YouTubeSearchListResponseDTO>> getDisasters() {
        ApiResponse<YouTubeSearchListResponseDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(youTubeService.fetchRecentNaturalDisastersPosts());
            apiResponse.setMessage("Recent YouTube Disaster Details Fetched");
            apiResponse.setHttpStatus(HttpStatus.OK);

        } catch (Exception e) {
            log.error("Failed to get YouTube Disaster Data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Failed to get YouTube Disaster Data");
        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
