package org.dersbian.vandiorsp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a token with a specific type, value, and source location in the code.")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "token_id_gen")
    @TableGenerator(
            name = "token_id_gen",
            table = "id_generator",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "token_id", // Explicit segment value
            allocationSize = 150
    )
    @Schema(description = "Unique identifier of the token", example = "1")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    @Schema(description = "Type of the token", example = "DOUBLE", requiredMode = Schema.RequiredMode.REQUIRED)
    @Builder.Default
    private TokenType type = TokenType.UNKNOWN;

    @Column(length = 128, nullable = false)
    @Schema(description = "Value of the token", example = "abc123xyz", maxLength = 128, requiredMode = Schema.RequiredMode.REQUIRED)
    @Builder.Default
    private String value = "";

    @Column(nullable = false)
    @Schema(description = "Timestamp when the token was created", example = "2023-11-05T14:30:00Z", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the token was last modified", example = "2023-11-06T09:20:00Z")
    private LocalDateTime lastModifiedAt;

    @OneToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "source_location_id", referencedColumnName = "id", nullable = false)
    @Schema(description = "Associated source location for the token")
    private CodeSourceLocation sourceLocation;

    // Additional constructors
    public Token(TokenType tokenType, String value, CodeSourceLocation sourceLocation, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.type = tokenType;
        this.value = value;
        this.sourceLocation = sourceLocation;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public Token(TokenType tokenType, CodeSourceLocation sourceLocation, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this(tokenType, "", sourceLocation, createdAt, lastModifiedAt);
    }

    public Token(TokenType tokenType, String value, CodeSourceLocation sourceLocation, LocalDateTime createdAt) {
        this(tokenType, value, sourceLocation, createdAt, null);
    }

    public Token(TokenType tokenType, CodeSourceLocation sourceLocation, LocalDateTime createdAt) {
        this(tokenType, "", sourceLocation, createdAt, null);
    }

    public boolean isType(TokenType type) {
        return this.type == type;
    }

    public boolean isTypeAnyOf(List<TokenType> tokenTypes) {
        return tokenTypes.contains(this.type);
    }

    @Override
    public String toString() {
        if (this.value.isEmpty()) {
            return String.format("Token(type: %s, sourceLocation: %s)", type, sourceLocation);
        } else {
            return String.format("Token(type: %s, value: '%s', sourceLocation: %s)", type, value, sourceLocation);
        }
    }

    public String compatToString() {
        if (this.value.isEmpty()) {
            return String.format("Tok(typ: %s, sloc: %s)", type, sourceLocation);
        } else {
            return String.format("Tok(typ: %s, val: '%s', sloc: %s)", type, value, sourceLocation.compatToString());
        }
    }

    @JsonIgnore
    public int getValueSize() {
        return this.value.length();
    }

}
