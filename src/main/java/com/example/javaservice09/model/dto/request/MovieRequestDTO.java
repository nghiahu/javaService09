package com.example.javaservice09.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDTO {
    @NotBlank(message = "Tiêu đề phim không được để trống")
    private String title;
    @NotBlank(message = "Miêu tả không được để trống")
    private String description;
    @NotNull(message = "Ngày phát hành không được để trống")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Ngày phát hành không đúng định dạng")
    private LocalDate releaseDate;
    private MultipartFile poster;
}
