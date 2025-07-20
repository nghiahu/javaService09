package com.example.javaservice09.controller;

import com.example.javaservice09.model.dto.request.MovieRequestDTO;
import com.example.javaservice09.model.dto.response.DataResponse;
import com.example.javaservice09.model.entity.Movie;
import com.example.javaservice09.service.CloudinaryService;
import com.example.javaservice09.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Movie>>> getAllMovies() {
        return new ResponseEntity<>(new DataResponse<>(movieService.getAllMovies(), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Movie>> addMovie(@ModelAttribute MovieRequestDTO movieRequestDTO) {
        String url = cloudinaryService.uploadImage(movieRequestDTO.getPoster());
        if (url == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            Movie movie = new Movie();
            movie.setPoster(url);
            movie.setTitle(movieRequestDTO.getTitle());
            movie.setDescription(movieRequestDTO.getDescription());
            movie.setReleaseDate(movieRequestDTO.getReleaseDate());
            return new ResponseEntity<>(new DataResponse<>(movieService.insertMovie(movie), HttpStatus.CREATED), HttpStatus.CREATED);
        }
    }
}
