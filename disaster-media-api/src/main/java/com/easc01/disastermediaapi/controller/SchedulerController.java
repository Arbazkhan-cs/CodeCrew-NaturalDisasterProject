package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.dto.ApiResponse;
import com.easc01.disastermediaapi.service.DisasterSchedulerService;
import com.easc01.disastermediaapi.util.IDUtil;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping(path = AppConstant.SCRAP)
public class SchedulerController {

    private final DisasterSchedulerService disasterSchedulerService;

    @PostMapping(AppConstant.DISASTER)
    public ResponseEntity<ApiResponse<Double>> scrapDisasters() {
        ApiResponse<Double> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(disasterSchedulerService.collectAndSaveDisastersFromYouTube());
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setMessage("Data scrapped successfully");
            log.error("Scrapped disaster data successfully");

        } catch (Exception e) {
            apiResponse.setMessage("Failed to scrap data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Failed to scrap disaster data");

        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }
}
