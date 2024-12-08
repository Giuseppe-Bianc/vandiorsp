package org.dersbian.vandiorsp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.dersbian.vandiorsp.model.TokenSummary;
import org.dersbian.vandiorsp.model.TokenType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for {@link org.dersbian.vandiorsp.model.TokenSummary}
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TokenSummaryDTO {

    @Schema(description = "Unique identifier of the token summary", example = "1")
    Long id;

    @Schema(description = "Type of the token", example = "ACCESS")
    TokenType type;

    @Schema(description = "File name associated with the token")
    FileNameDTO fileName;

    @Schema(description = "Count of tokens", example = "100")
    Long tokenCount;

    /**
     * Converts a TokenSummary entity to a TokenSummaryDTO.
     *
     * @param tokenSummary the TokenSummary entity
     * @return the TokenSummaryDTO
     */
    public static TokenSummaryDTO fromEntity(TokenSummary tokenSummary) {
        return TokenSummaryDTO.builder()
                .id(tokenSummary.getId())
                .type(tokenSummary.getType())
                .fileName(FileNameDTO.builder()
                        .id(tokenSummary.getFileName().getId())
                        .name(tokenSummary.getFileName().getName())
                        .build())
                .tokenCount(tokenSummary.getTokenCount())
                .build();
    }
}