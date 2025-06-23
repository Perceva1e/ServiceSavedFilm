package com.example.servicesavedfilm.service;

import com.example.servicesavedfilm.dto.SavedFilmDTO;
import com.example.servicesavedfilm.model.SavedFilm;
import com.example.servicesavedfilm.repository.SavedFilmRepository;
import com.example.servicesavedfilm.client.FilmClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavedFilmService {
    private final SavedFilmRepository savedFilmRepository;
    private final FilmClient filmClient;

    public SavedFilmDTO saveFilm(Long userId, Long filmId) {
        log.info("Saving film with ID: {} for user ID: {} at {}", filmId, userId, LocalDateTime.now());
        SavedFilm savedFilm = new SavedFilm();
        savedFilm.setUserId(userId);
        savedFilm.setFilmId(filmId);
        savedFilm.setSavedAt(LocalDateTime.now());
        SavedFilm saved = savedFilmRepository.save(savedFilm);
        log.debug("Saved film with ID: {} at {}", saved.getId(), LocalDateTime.now());
        return mapToDTO(saved);
    }

    public List<SavedFilmDTO> getSavedFilmsByUser(Long userId) {
        log.info("Fetching saved films for user ID: {} at {}", userId, LocalDateTime.now());
        List<SavedFilm> savedFilms = savedFilmRepository.findByUserId(userId);
        log.debug("Retrieved {} saved films at {}", savedFilms.size(), LocalDateTime.now());
        return savedFilms.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void deleteSavedFilm(Long id) {
        log.info("Deleting saved film with ID: {} at {}", id, LocalDateTime.now());
        savedFilmRepository.deleteById(id);
        log.debug("Deleted saved film with ID: {} at {}", id, LocalDateTime.now());
    }

    private SavedFilmDTO mapToDTO(SavedFilm savedFilm) {
        SavedFilmDTO dto = new SavedFilmDTO();
        dto.setId(savedFilm.getId());
        dto.setUserId(savedFilm.getUserId());
        dto.setSavedAt(savedFilm.getSavedAt());

        try {
            SavedFilmDTO.FilmDTO film = filmClient.getFilmById(savedFilm.getFilmId());
            dto.setFilm(film);
        } catch (Exception e) {
            log.warn("Failed to fetch film details for ID: {}, error: {}", savedFilm.getFilmId(), e.getMessage());
            dto.setFilm(new SavedFilmDTO.FilmDTO());
        }
        return dto;
    }
}