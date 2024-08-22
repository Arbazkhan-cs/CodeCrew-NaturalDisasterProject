package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;

import java.util.List;

public interface GenerativeAIService {

    List<AIProcessedDisaster> processRawDisasterData(List<RawDisasterData> rawDisasterData);

}
