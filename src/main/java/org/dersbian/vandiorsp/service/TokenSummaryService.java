package org.dersbian.vandiorsp.service;

import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsq.models.FileName;
import org.dersbian.vandiorsq.models.TokenSummary;
import org.dersbian.vandiorsq.models.dto.TokenSummaryDto;
import org.dersbian.vandiorsq.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TokenSummaryService {

    private final FileNameRepository fileNameRepository;
    private final TokenSummaryRepository tokenSummaryRepository;

    @Autowired
    public TokenSummaryService(FileNameRepository fileNameRepository, TokenSummaryRepository tokenSummaryRepository) {
        this.fileNameRepository = fileNameRepository;
        this.tokenSummaryRepository = tokenSummaryRepository;
    }

    /**
     * Inserts TokenSummary records for all TokenType values by counting tokens in each FileName.
     */
    public void saveTokenSummaries() {
        List<TokenSummaryDto> tokenSummaryDtos = tokenSummaryRepository.findTokenSummaries();
        Map<Long, FileName> fileNameCache = new HashMap<>(tokenSummaryDtos.size());

        List<TokenSummary> tokenSummaries = tokenSummaryDtos.stream()
                .map(tokenSummaryDto -> {
                    FileName fileName = fileNameCache.computeIfAbsent(
                            tokenSummaryDto.getFileName().getId(),
                            this::findFileNameOrThrow);
                    return TokenSummary.builder()
                            .type(tokenSummaryDto.getType())
                            .tokenCount(tokenSummaryDto.getTokenCount())
                            .fileName(fileName)
                            .build();
                })
                .collect(Collectors.toList());

        tokenSummaryRepository.saveAll(tokenSummaries);
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
