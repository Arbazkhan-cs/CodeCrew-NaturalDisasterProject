package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.youtube.YouTubeSearchListResponseDTO;

public interface YouTubeService {

    /**
     * Fetches Recent Natural disasters posts from YouTube
     * @return YouTubeSearchListResponse DTO, the result of YouTube Data API
     */
    YouTubeSearchListResponseDTO fetchRecentNaturalDisastersPosts();
}
