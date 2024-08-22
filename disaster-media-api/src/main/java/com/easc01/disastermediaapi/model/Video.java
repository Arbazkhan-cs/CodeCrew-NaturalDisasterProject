package com.easc01.disastermediaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@Table(name = "video")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;
    private String url;

    @Column(length = 5000)
    private String description;
    private String userId;
    private Instant publishedDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;
}
