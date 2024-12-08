package org.dersbian.vandiorsp.modello.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.dersbian.vandiorsq.models.FileName;
import org.dersbian.vandiorsq.models.TokenSummary;
import org.dersbian.vandiorsq.models.TokenType;

import java.io.Serializable;

/**
 * DTO for {@link TokenSummary}
 */
@Data
@NoArgsConstructor
public class TokenSummaryDto implements Serializable {
    TokenType type;
    FileNameDto fileName;
    Long tokenCount;
    /**
     * DTO for {@link FileName}
     */

    @Data
    @Value
    public static class FileNameDto implements Serializable {
        Long id;
    }

    public TokenSummaryDto(Long tokenCount, Long fileName, TokenType type) {
        this.type = type;
        this.fileName = new FileNameDto(fileName);
        this.tokenCount = tokenCount;
    }
}