package com.easc01.disastermediaapi.repository;

import com.easc01.disastermediaapi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
