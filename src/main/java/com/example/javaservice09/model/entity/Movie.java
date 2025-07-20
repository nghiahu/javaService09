package com.example.javaservice09.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(length = 100)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/M/yyyy")
    private LocalDate releaseDate;
    private String poster;
}
