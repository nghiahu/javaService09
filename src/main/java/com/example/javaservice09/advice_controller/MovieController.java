package com.example.javaservice09.advice_controller;

import com.example.javaservice09.model.dto.request.MovieRequestDTO;
import com.example.javaservice09.model.entity.Movie;
import com.example.javaservice09.service.LogService;
import com.example.javaservice09.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private LogService logService;

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie saved = movieService.insertMovie(movie);
            logger.info("\\u001B[32mThêm phim: {} lúc {}\\u001B[0m", saved.getTitle(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            logger.error("\\u001B[31mLỗi khi thêm phim: {}\\u001B[0m", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi thêm phim");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movieRequestDTO) {
        try {
            Movie oldMovie = movieService.getMovieById(id);

            Movie updatedMovie = new Movie();
            updatedMovie.setTitle(movieRequestDTO.getTitle());
            updatedMovie.setDescription(movieRequestDTO.getDescription());
            updatedMovie.setReleaseDate(movieRequestDTO.getReleaseDate());
            updatedMovie.setId(oldMovie.getId());
            updatedMovie.setPoster(oldMovie.getPoster());
            logger.info("\u001B[33mThông tin cũ: {}\u001B[0m", oldMovie);
            logger.info("\u001B[32mThông tin mới: {}\u001B[0m", updatedMovie);

            return ResponseEntity.ok(updatedMovie);
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi cập nhật phim: {}\u001B[0m", e.getMessage(), e); // Đỏ
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi cập nhật phim");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {

            Movie movieToDelete = movieService.getMovieById(id);
            movieService.deleteMovie(id);
            logger.info("\u001B[31mXoá thành công\u001B[0m - \u001B[32m{}\u001B[0m", movieToDelete);

            return ResponseEntity.ok("Xoá phim thành công");
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi xoá phim: {}\u001B[0m", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xoá phim");
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        List<Movie> movies = movieService.getAllMovies(searchMovie);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search-logs")
    public Map<String, Integer> getSearchLogs() {
        return logService.getSearchKeywordStats();
    }

    @GetMapping("/suggestions")
    public List<Movie> getSuggestions() {
        Set<String> keywords = logService.getAllSearchKeywords();
        return movieService.getSuggestedMovies(keywords);
    }
}