package org.dersbian.vandiorsp.service;

import lombok.extern.slf4j.Slf4j;
import org.dersbian.vandiorsp.model.CodeSourceLocation;
import org.dersbian.vandiorsp.model.FileName;
import org.dersbian.vandiorsp.model.Lexer;
import org.dersbian.vandiorsp.model.Token;
import org.dersbian.vandiorsp.repository.CodeSourceLocationRepository;
import org.dersbian.vandiorsp.repository.FileNameRepository;
import org.dersbian.vandiorsp.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final CodeSourceLocationRepository codeSourceLocationRepository;
    private final FileNameRepository fileNameRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository,
                        CodeSourceLocationRepository codeSourceLocationRepository,
                        FileNameRepository fileNameRepository) {
        this.tokenRepository = tokenRepository;
        this.codeSourceLocationRepository = codeSourceLocationRepository;
        this.fileNameRepository = fileNameRepository;
    }

    /*
     * Creates and saves a Token entity with its associated CodeSourceLocation and FileName.
    @Transactional
    public Token createToken(TokenType type, String value, String fileNameString, int line, int column) {
        var fileName = getOrCreateFileName(fileNameString);
        var sourceLocation = saveCodeSourceLocation(fileName, line, column);

        return tokenRepository.save(Token.builder()
                .type(type)
                .value(value)
                .sourceLocation(sourceLocation)
                .build());
    }*/

    /**
     * Saves an individual Token, ensuring all associated entities are handled properly.
     */
    @Transactional
    public void saveToken(Token token) {
        if (!Objects.isNull(token.getId())) {
            throw new IllegalArgumentException("in the creation fase of the token the id must be empty");
        }
        Optional.ofNullable(token.getSourceLocation())
                .ifPresent(sourceLocation -> {
                    var fileName = getOrCreateFileName(sourceLocation.getFileName().getName());
                    var savedLocation = saveOrRetrieveSourceLocation(sourceLocation, fileName);
                    token.setSourceLocation(savedLocation);
                });
        tokenRepository.save(token);
    }

    /**
     * Saves a list of Tokens in a single transaction, handling related entities efficiently.
     */
    @Transactional
    public void saveTokens(List<Token> tokens) {
        Map<String, FileName> fileNameCache = new HashMap<>();
        List<CodeSourceLocation> sourceLocationsToSave = new ArrayList<>(tokens.size()); // Preallocate size

        tokens.forEach(token -> Optional.ofNullable(token.getSourceLocation()).ifPresent(sourceLocation -> {
                    CodeSourceLocation savedLocation = processSourceLocation(sourceLocation, fileNameCache);
                    token.setSourceLocation(savedLocation);
                    if (Objects.isNull(savedLocation.getId())) {
                        sourceLocationsToSave.add(savedLocation);
                    }
                })
        );

        saveNewSourceLocations(sourceLocationsToSave);
        tokenRepository.saveAll(tokens);
    }

    private CodeSourceLocation processSourceLocation(CodeSourceLocation sourceLocation, Map<String, FileName> fileNameCache) {
        String fileNameStr = sourceLocation.getFileName().getName();
        var fileName = fileNameCache.computeIfAbsent(fileNameStr, this::getOrCreateFileName);
        return saveOrRetrieveSourceLocation(sourceLocation, fileName);
    }

    private void saveNewSourceLocations(List<CodeSourceLocation> sourceLocationsToSave) {
        if (!sourceLocationsToSave.isEmpty()) {
            codeSourceLocationRepository.saveAll(sourceLocationsToSave);
        }
    }

    /**
     * Helper method to retrieve an existing FileName or create a new one if it doesn't exist.
     */
    private FileName getOrCreateFileName(String fileNameString) {
        return fileNameRepository.findByName(fileNameString)
                .orElseGet(() -> fileNameRepository.save(new FileName(fileNameString)));
    }

    /**
     * Helper method to save or retrieve a CodeSourceLocation based on fileName, line, and column.
     */
    private CodeSourceLocation saveOrRetrieveSourceLocation(CodeSourceLocation sourceLocation, FileName fileName) {
        sourceLocation.setFileName(fileName);
        return Optional.ofNullable(sourceLocation.getId())
                .map(id -> sourceLocation)
                .orElseGet(() -> codeSourceLocationRepository.save(sourceLocation));
    }

    /**
     * Helper method to create and save a new CodeSourceLocation.
     * private CodeSourceLocation saveCodeSourceLocation(FileName fileName, int line, int column) {
     * var sourceLocation = CodeSourceLocation.builder()
     * .fileName(fileName)
     * .line(line)
     * .column(column)
     * .build();
     * return codeSourceLocationRepository.save(sourceLocation);
     * }
     */

    public Token findToken(Long id) {
        return tokenRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("the token whit id: " + id + "not found"));
    }

    public List<Token> getTokens() {
        return tokenRepository.findAll();
    }

    private String readFileAsString(MultipartFile file) throws IOException {
        // Convert the file's bytes to a string using the specified charset (UTF-8 by default)
        return new String(file.getBytes(), StandardCharsets.UTF_8);
    }

    @Transactional
    public List<Token> parseFile(MultipartFile file) throws IOException {
        String fileContent = readFileAsString(file);
        String fileName = file.getOriginalFilename();
        Lexer lexer = new Lexer(fileName, fileContent);
        List<Token> tokens = lexer.tokenize();
        saveTokens(tokens);
        return tokens;
    }
}
