package org.dersbian.vandiorsp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public record CustomValidationExceptionsResponse(Map<String, String> messages,
                                                 String path,
                                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                                 LocalDateTime timestamp) {
    public CustomValidationExceptionsResponse(String path) {
        this(new HashMap<>(), path, LocalDateTime.now());
    }

    public String put(String key, String value) {
        return messages.put(key, value);
    }
}