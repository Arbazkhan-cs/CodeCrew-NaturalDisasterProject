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
    private String thumbnail;

    @Column(length = 5000)
    private String description;
    private String userId;
    private Instant publishedDate;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "disaster_id")
    private Disaster disaster;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return url != null && url.equals(video.url);
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
