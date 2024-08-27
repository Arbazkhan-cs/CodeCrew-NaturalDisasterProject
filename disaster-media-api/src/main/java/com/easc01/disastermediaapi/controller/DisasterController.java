package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.dto.ApiResponse;
import com.easc01.disastermediaapi.dto.disaster.DisasterData;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import com.easc01.disastermediaapi.service.DisasterService;
import com.easc01.disastermediaapi.util.IDUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Disasters", description = "Following endpoints response with natural disaster data.")
@RequestMapping(path = AppConstant.DISASTER)
public class DisasterController {

    private final DisasterService disasterService;
    private final DisasterRepository disasterRepository;

    @GetMapping(value = AppConstant.ALL)
    @Operation(description = "Returns natural disasters from the entire archive by certain criteria, leave params blank to ignore.")
    public ResponseEntity<ApiResponse<List<DisasterData>>> getAllDisasterData(
            @RequestParam(name = "searchTag") String searchTag,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "location") String location,
            @RequestParam(name = "publishedBefore") String publishedBefore,
            @RequestParam(name = "publishedAfter") String publishedAfter
    ) {
        ApiResponse<List<DisasterData>> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(
                    disasterService.getProcessedDisasterDataByCriteria(
                            searchTag.trim(),
                            type.trim(),
                            location.trim(),
                            publishedBefore.isBlank() ? String.valueOf(Instant.now()) : publishedBefore,
                            publishedAfter.isBlank() ? String.valueOf(Instant.EPOCH) : publishedAfter
                    )
            );
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setMessage("Fetched Disaster Data");

        } catch (Exception e) {
            log.error("Failed to fetch disaster data");
            apiResponse.setMessage("Failed to fetch disaster data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping(value = AppConstant.RECENT)
    @Operation(description = "Returns natural disasters that were broadcast in the past 15 minutes")
    public ResponseEntity<ApiResponse<List<DisasterData>>> getRecentDisasterData(
            @RequestParam(name = "searchTag") String searchTag,
            @RequestParam(name = "type") String type,
            @RequestParam(name = "location") String location
    ) {
        ApiResponse<List<DisasterData>> apiResponse = new ApiResponse<>();
        apiResponse.setRequestId(String.valueOf(IDUtil.generateHttpRequestId()));

        try {
            apiResponse.setData(
                    disasterService.getProcessedDisasterDataByCriteria(
                            searchTag.trim(),
                            type.trim(),
                            location.trim(),
                            String.valueOf(Instant.now()),
                            String.valueOf(Instant.now().minus(15, ChronoUnit.MINUTES))
                    )
            );
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setMessage("Fetched Recent Disaster Data");

        } catch (Exception e) {
            log.error("Failed to fetch recent disaster data");
            apiResponse.setMessage("Failed to fetch recent disaster data");
            apiResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        apiResponse.setTimestamp(Date.from(Instant.now()));
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }

    @GetMapping(value = "/search-record")
    @Operation(description = "Returns natural disasters by Record ID.")
    public ResponseEntity<?> getByRecordId(
            @RequestParam(name = "id") String recordId
    ) {
        try {
            Optional<Disaster> disasterOptional = disasterRepository.findByRecordIdWithVideos(recordId);
            if (disasterOptional.isPresent()) {
                return new ResponseEntity<>(disasterOptional.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Not Found", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}