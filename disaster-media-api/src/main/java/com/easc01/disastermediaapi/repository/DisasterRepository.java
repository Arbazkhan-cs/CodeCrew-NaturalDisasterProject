package com.easc01.disastermediaapi.repository;

import com.easc01.disastermediaapi.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    @Query("SELECT d FROM Disaster d LEFT JOIN FETCH d.videos")
    List<Disaster> findAllWithVideos();

    @Query("SELECT d FROM Disaster d LEFT JOIN FETCH d.videos WHERE d.recordId = :recordId")
    Optional<Disaster> findByRecordIdWithVideos(@Param("recordId") String recordId);

    @Query("SELECT DISTINCT d FROM Disaster d " +
            "JOIN FETCH d.videos v " +
            "WHERE (:searchTag = '' OR LOWER(d.title) LIKE LOWER(CONCAT('%', :searchTag, '%')) " +
            "OR LOWER(d.summary) LIKE LOWER(CONCAT('%', :searchTag, '%'))) " +
            "AND (:type = '' OR LOWER(d.incidentType) LIKE LOWER(CONCAT('%', :type, '%'))) " +
            "AND (:location = '' OR LOWER(d.incidentLocation) LIKE LOWER(CONCAT('%', :location, '%'))) " +
            "AND v.publishedDate BETWEEN :startDate AND :endDate " +
            "ORDER BY d.updatedAt DESC")
    List<Disaster> findDisastersByCriteria(
            @Param("searchTag") String searchTag,
            @Param("type") String type,
            @Param("location") String location,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );

    @Query("SELECT d FROM Disaster d LEFT JOIN FETCH d.videos WHERE d.recordId IN :recordIds")
    List<Disaster> findAllByRecordIdIn(@Param("recordIds") Set<String> recordIds);
}
