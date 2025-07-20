package com.example.javaservice09.service;

import com.example.javaservice09.model.entity.Movie;
import com.example.javaservice09.repository.MoviRepository;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MoviRepository moviRepository;

    public List<Movie> getAllMovies(String searchMovie) {

        List<Movie> result;
        if (searchMovie != null && !searchMovie.trim().isEmpty()) {
            result = moviRepository.findByTitleContainingIgnoreCase(searchMovie);
        } else {
            result = moviRepository.findAll();
        }

        logger.info("\u001B[32mSearch keyword: {}\u001B[0m", searchMovie);
        logger.info("\u001B[32mNumber of movies found: {}\u001B[0m", result.size());
        return result;
    }

    public Movie getMovieById(Long movieId) {
        return moviRepository.findById(movieId).orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }

    public Movie insertMovie(Movie movie) {
        return moviRepository.save(movie);
    }

    public Movie updateMovie(Long movieId, Movie movie) {
        moviRepository.findById(movieId).orElseThrow(() -> new NoSuchElementException("Movie not found"));
        return moviRepository.save(movie);
    }

    public void deleteMovie(Long movieId) {
        moviRepository.findById(movieId).orElseThrow(() -> new NoSuchElementException("Movie not found"));
        moviRepository.deleteById(movieId);
    }

    public List<Movie> getSuggestedMovies(Set<String> keywords) {
        Set<Movie> suggested = new HashSet<>();
        for (String keyword : keywords) {
            suggested.addAll(moviRepository.findByTitleContainingIgnoreCase(keyword));
        }
        return new ArrayList<>(suggested);
    }

}
