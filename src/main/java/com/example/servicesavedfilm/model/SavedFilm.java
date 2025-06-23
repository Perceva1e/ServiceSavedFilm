package com.example.servicesavedfilm.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "saved_films")
@Data
public class SavedFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "film_id", nullable = false)
    private Long filmId;

    @Column(name = "saved_at", nullable = false)
    private LocalDateTime savedAt;
}