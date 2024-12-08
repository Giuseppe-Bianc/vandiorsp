package org.dersbian.vandiorsp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class TokenSummaryKey {
    private final TokenType type;
    private final FileName fileName;
}
