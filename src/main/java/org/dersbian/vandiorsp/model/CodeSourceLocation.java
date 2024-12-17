package org.dersbian.vandiorsp.model;

//import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Represents a location in the code source, including file name, line, and column information.")
public class CodeSourceLocation {

    @Id
    /*@GeneratedValue(strategy = GenerationType.TABLE, generator = "code_source_Location_id_gen")
    @TableGenerator(
            name = "code_source_Location_id_gen",
            table = "id_generator",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "code_source_location_id",
            allocationSize = 150
    )*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_source_location_seq_gen")
    @SequenceGenerator(
            name = "code_source_location_seq_gen",
            sequenceName = "code_source_location_seq",
            allocationSize = 150
    )
    @Schema(description = "Unique identifier for the code source location", example = "1")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_name_id", columnDefinition = "SMALLINT", nullable = false)
    @Schema(description = "The file associated with this code source location")
    private FileName fileName;

    @Column(columnDefinition = "SMALLINT", nullable = false)
    @Builder.Default
    @Schema(description = "Line number in the file where the code is located", example = "42")
    private int line = 0;

    @Column(name = "colum", columnDefinition = "SMALLINT", nullable = false)
    @Builder.Default
    @Schema(description = "Column number in the file where the code is located", example = "10")
    private int column = 0;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Timestamp when the token was created", example = "2023-11-05T14:30:00Z")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Timestamp when the token was last modified", example = "2024-12-07T09:20:00Z")
    private LocalDateTime lastModifiedAt;

    public CodeSourceLocation(FileName fileName, int line, int column) {
        this.fileName = fileName;
        this.line = line;
        this.column = column;
    }

    public static CodeSourceLocation unknown() {
        return new CodeSourceLocation(new FileName("unknown"), 0, 0);
    }

    @Override
    public String toString() {
        return String.format("(file: '%s', line: %d, column: %d)", fileName, line, column);
    }

    public String compatToString() {
        return String.format("(fn: '%s', ln: %d, col: %d)", fileName, line, column);
    }
}
