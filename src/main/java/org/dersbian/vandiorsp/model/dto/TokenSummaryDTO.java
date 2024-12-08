package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dersbian.vandiorsp.model.TokenSummary;
import org.dersbian.vandiorsp.model.TokenType;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.TokenSummary}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TokenSummaryDTO {
    Long id;
    TokenType type;
    FileNameDTO fileName;
    Long tokenCount;

    public static TokenSummaryDTO fromEntity(TokenSummary tokenSummary) {
        return TokenSummaryDTO.builder().id(tokenSummary.getId())
                .type(tokenSummary.getType())
                .fileName(FileNameDTO.builder().id(tokenSummary.getFileName().getId()).name(tokenSummary.getFileName().getName()).build())
                .tokenCount(tokenSummary.getTokenCount())
                .build();
    }
}