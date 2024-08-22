package com.easc01.disastermediaapi.service.impl;

import com.easc01.disastermediaapi.dto.generativeai.AIProcessedDisaster;
import com.easc01.disastermediaapi.dto.generativeai.RawDisasterData;
import com.easc01.disastermediaapi.service.GenerativeAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenerativeAIServiceImpl implements GenerativeAIService {

    @Override
    public List<AIProcessedDisaster> processRawDisasterData(List<RawDisasterData> rawDisasterData) {
        return List.of();
    }
}
