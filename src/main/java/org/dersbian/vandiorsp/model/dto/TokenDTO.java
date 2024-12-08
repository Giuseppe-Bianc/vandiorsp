package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dersbian.vandiorsp.model.TokenType;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.Token}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    @Schema(description = "Unique identifier of the token", example = "1")
    Long id;

    @Schema(description = "Type of the token", example = "ACCESS")
    TokenType type;

    @Schema(description = "Value of the token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
    String value;

    @Schema(description = "Creation timestamp of the token", example = "2023-01-01T12:00:00")
    LocalDateTime createdAt;

    @Schema(description = "Last modification timestamp of the token", example = "2023-01-02T12:00:00")
    LocalDateTime lastModifiedAt;

    @Schema(description = "Source location of the code associated with the token")
    CodeSourceLocationDTO sourceLocation;
}