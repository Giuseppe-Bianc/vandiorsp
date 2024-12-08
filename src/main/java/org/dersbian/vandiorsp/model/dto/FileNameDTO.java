package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for {@link org.dersbian.vandior_restsql.models.FileName}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FileNameDTO {
    Long id;
    String name;
}