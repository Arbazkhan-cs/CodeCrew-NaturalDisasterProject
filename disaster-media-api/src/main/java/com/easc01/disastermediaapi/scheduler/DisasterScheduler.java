package com.easc01.disastermediaapi.scheduler;


import com.easc01.disastermediaapi.service.DisasterSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DisasterScheduler {

    private final DisasterSchedulerService disasterSchedulerService;

//    @Scheduled(initialDelay = 2000, fixedDelay = 900000)
    public void fetchAndSaveDiastersFromYouTube() {
        disasterSchedulerService.collectAndSaveDisastersFromYouTube();
    }

}