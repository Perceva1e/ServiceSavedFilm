package com.example.servicesavedfilm.client;

import com.example.servicesavedfilm.dto.SavedFilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FilmClient {

    private final RestTemplate restTemplate;

    @Autowired
    public FilmClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SavedFilmDTO.FilmDTO getFilmById(Long filmId) {
        return restTemplate.getForObject("/films/{id}", SavedFilmDTO.FilmDTO.class, filmId);
    }
}