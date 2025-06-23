package com.example.servicesavedfilm.repository;

import com.example.servicesavedfilm.model.SavedFilm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedFilmRepository extends JpaRepository<SavedFilm, Long> {
    List<SavedFilm> findByUserId(Long userId);
}