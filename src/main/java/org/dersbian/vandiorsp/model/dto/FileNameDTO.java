package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.FileName}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileNameDTO {

    @Schema(description = "Unique identifier of the file name", example = "1")
    Long id;

    @Schema(description = "Name of the file", example = "example.txt")
    String name;
}