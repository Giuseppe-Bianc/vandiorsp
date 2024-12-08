package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dersbian.vandiorsp.model.TokenType;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.Token}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    Long id;
    TokenType type;
    String value;
    LocalDateTime createdAt;
    LocalDateTime lastModifiedAt;
    CodeSourceLocationDTO sourceLocation;
}