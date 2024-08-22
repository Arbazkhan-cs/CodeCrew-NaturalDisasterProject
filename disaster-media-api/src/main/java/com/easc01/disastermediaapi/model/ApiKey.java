package com.easc01.disastermediaapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_key")
public class ApiKey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String email;


    private Instant createdAt;
    private Instant validTill;

    @Column(unique = true)
    private String key;
}
