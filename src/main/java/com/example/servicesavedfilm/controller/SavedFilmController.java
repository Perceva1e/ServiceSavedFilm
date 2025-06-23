package com.example.servicesavedfilm.controller;

import com.example.servicesavedfilm.dto.SavedFilmDTO;
import com.example.servicesavedfilm.service.SavedFilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-films")
@Slf4j
@RequiredArgsConstructor
public class SavedFilmController {

    private final SavedFilmService savedFilmService;

    @PostMapping
    public ResponseEntity<Void> saveFilm(
            @RequestParam Long userId,
            @RequestParam Long filmId) {
        savedFilmService.saveFilm(userId, filmId);
        log.info("Saved film with ID: {} for user ID: {}", filmId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedFilmDTO>> getSavedFilmsByUser(@PathVariable Long userId) {
        List<SavedFilmDTO> films = savedFilmService.getSavedFilmsByUser(userId);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedFilm(@PathVariable Long id) {
        log.info("Deleting saved film with ID: {}", id);
        savedFilmService.deleteSavedFilm(id);
        return ResponseEntity.noContent().build();
    }
}