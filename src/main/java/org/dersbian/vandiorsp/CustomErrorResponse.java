package org.dersbian.vandiorsp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDateTime;

public record CustomErrorResponse(String message,
                                  String path,
                                  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                  LocalDateTime timestamp) {
    public CustomErrorResponse(String message, String path) {
        this(message, path, LocalDateTime.now());
    }
}
