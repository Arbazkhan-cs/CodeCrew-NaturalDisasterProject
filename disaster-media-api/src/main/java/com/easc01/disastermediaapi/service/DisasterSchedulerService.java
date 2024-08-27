package com.easc01.disastermediaapi.service;

public interface DisasterSchedulerService {

    /**
     * Collects Disaster content like 'tsunami, flood, earthquake, etc' from YouTube,
     * Processes the fetch data under AI,
     * Then saves the processed data into database
     * @return Time taken in seconds to scrap, process and saving disaster data from youtube
     */
    double collectAndSaveDisastersFromYouTube();
}
