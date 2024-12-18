package org.dersbian.vandiorsp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class CustomValidationExceptionsResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String path;


    private Map<String, String> messages = new HashMap<>();

    public CustomValidationExceptionsResponse(String path) {
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }

    public String put(String key, String value) {
        return messages.put(key, value);
    }

}
