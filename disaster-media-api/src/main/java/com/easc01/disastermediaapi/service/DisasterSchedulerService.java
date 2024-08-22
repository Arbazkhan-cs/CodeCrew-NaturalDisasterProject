package com.easc01.disastermediaapi.service;

public interface DisasterSchedulerService {

    /**
     * Collects Disaster content like 'tsunami, flood, earthquake, etc' from YouTube,
     * Processes the fetch data under AI,
     * Then saves the processed data into database
     */
    void collectAndSaveDisastersFromYouTube();
}
