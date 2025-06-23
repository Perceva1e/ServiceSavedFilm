package com.example.servicesavedfilm.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SavedFilmDTO {
    private Long id;
    private Long userId;
    private FilmDTO film;
    private LocalDateTime savedAt;

    @Data
    public static class FilmDTO {
        private Long id;
        private String title;
        private int releaseYear;
        private String originalLanguage;
        private Integer duration;
        private Double rating;
        private FilmDataDTO filmData;
        private List<GenreDTO> genres;
        private List<PersonnelDTO> personnel;

        @Data
        public static class FilmDataDTO {
            private Long id;
            private double rating;
            private double budget;
            private String poster;
            private String trailer;
            private double revenue;
        }

        @Data
        public static class GenreDTO {
            private Long id;
            private String name;
            private String description;
        }

        @Data
        public static class PersonnelDTO {
            private Long id;
            private PersonDTO person;
            private String role;

            @Data
            public static class PersonDTO {
                private Long id;
                private String name;
                private String biography;
                private String photograph;
            }
        }
    }
}