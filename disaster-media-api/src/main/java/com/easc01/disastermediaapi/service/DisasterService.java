package com.easc01.disastermediaapi.service;

import com.easc01.disastermediaapi.dto.disaster.DisasterData;

import java.util.List;

public interface DisasterService {

    List<DisasterData> getProcessedDisasterDataByCriteria(
            String searchTag,
            String incidentType,
            String incidentLocation,
            String publishedBefore,
            String publishedAfter
    );


}
