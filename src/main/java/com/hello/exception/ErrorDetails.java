package com.hello.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorDetails {
    private final String message;
    private final HttpStatus httpStatus;
    private final String details;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
    private LocalDateTime dateTime;

    public ErrorDetails(String message, HttpStatus httpStatus, String details) {
        dateTime = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
    }
}
