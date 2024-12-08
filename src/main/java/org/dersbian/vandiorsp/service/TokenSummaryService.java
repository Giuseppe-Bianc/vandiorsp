package org.dersbian.vandiorsp.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsp.model.*;
import org.dersbian.vandiorsp.model.dto.TokenSummaryDTO;
import org.dersbian.vandiorsp.repository.CodeSourceLocationRepository;
import org.dersbian.vandiorsp.repository.FileNameRepository;
import org.dersbian.vandiorsp.repository.TokenRepository;
import org.dersbian.vandiorsp.repository.TokenSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TokenSummaryService {

    private final FileNameRepository fileNameRepository;
    private final TokenRepository tokenRepository;
    private final CodeSourceLocationRepository codeSourceLocationRepository;
    private final TokenSummaryRepository tokenSummaryRepository;

    /**
     * Inserts TokenSummary records for all TokenType values by counting tokens in each FileName.
     */
    public List<TokenSummaryDTO> saveTokenSummaries() {
        List<Token> tokens = tokenRepository.findAll();
        // Group tokens by type and file name, then count occurrences
        Map<TokenSummaryKey, Long> summaryMap = tokens.stream()
                .collect(Collectors.groupingBy(
                        token -> new TokenSummaryKey(token.getType(), token.getSourceLocation().getFileName()),
                        Collectors.counting()
                ));

        // Convert the map to TokenSummary entities
        List<TokenSummary> summaries = summaryMap.entrySet().stream()
                .map(entry -> TokenSummary.builder()
                        .type(entry.getKey().getType())
                        .fileName(entry.getKey().getFileName())
                        .tokenCount(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        // Save all summaries
        tokenSummaryRepository.saveAll(summaries);
        return summaries.stream()
                .map(TokenSummaryDTO::fromEntity)
                .collect(Collectors.toList());
    }



    private FileName findFileNameOrThrow(Long filenameID) {
        return fileNameRepository.findById(filenameID)
                .orElseThrow(() -> new FileNameNotFoundException("FileName not found for ID: " + filenameID));
    }

    // Custom exception for clarity
    public static class FileNameNotFoundException extends RuntimeException {
        public FileNameNotFoundException(String message) {
            super(message);
        }
    }
}
