package org.dersbian.vandiorsp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String path;
    private String message;

    public CustomErrorResponse(String message, String path) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
}
