package com.easc01.disastermediaapi.repository;

import com.easc01.disastermediaapi.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
}
