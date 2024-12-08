package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.dersbian.vandior_restsql.models.CodeSourceLocation}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CodeSourceLocationDTO {
    Long id;
    FileNameDTO fileName;
    int line;
    int column;
    LocalDateTime createdAt;
    LocalDateTime lastModifiedAt;
}