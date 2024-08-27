package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.dto.ApiResponse;
import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.GenerativeAIService;
import com.easc01.disastermediaapi.util.IDUtil;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping(path = AppConstant.AI)
public class AIController {

    private final GenerativeAIService generativeAIService;
    private final DisasterRepository disasterRepository;

    @PostMapping(path = AppConstant.PROCESS)
    public ResponseEntity<ApiResponse<List<AIProcessedDisaster>>> processDisasters(
            @RequestBody List<RawDisasterData> rawDisasterData
    ) {
        ApiResponse<List<AIProcessedDisaster>> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(generativeAIService.processRawDisasterData(rawDisasterData));
            apiResponse.setMessage("Processed disaster data");
            apiResponse.setHttpStatus(HttpStatus.OK);

        } catch (Exception e) {
            log.error("Failed to process Disaster Data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            apiResponse.setMessage("Failed to process Disaster Data");
        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

}
