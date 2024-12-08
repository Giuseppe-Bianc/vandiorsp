package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.CodeSourceLocation}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CodeSourceLocationDTO {

    @Schema(description = "Unique identifier of the code source location", example = "1")
    Long id;

    @Schema(description = "File name associated with the code source location")
    FileNameDTO fileName;

    @Schema(description = "Line number in the file", example = "42")
    int line;

    @Schema(description = "Column number in the file", example = "10")
    int column;

    @Schema(description = "Creation timestamp of the code source location", example = "2023-01-01T12:00:00")
    LocalDateTime createdAt;

    @Schema(description = "Last modification timestamp of the code source location", example = "2023-01-02T12:00:00")
    LocalDateTime lastModifiedAt;
}