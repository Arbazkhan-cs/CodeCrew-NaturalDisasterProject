package com.easc01.disastermediaapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "disaster")
@NoArgsConstructor
@AllArgsConstructor
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String recordId;

    private String title;

    @Column(length = 5000)
    private String summary;
    private String incidentLocation;
    private String incidentType;

    @JsonManagedReference
    @OneToMany(mappedBy = "disaster", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Video> videos;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
