package com.example.javaservice09.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataErrorResponse<T> {
    private String message;
    private T error;
    private HttpStatus code;
}