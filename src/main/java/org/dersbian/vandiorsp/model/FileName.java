package org.dersbian.vandiorsp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a file name entity with a unique identifier and name.")
public class FileName {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "file_name_id_gen")
    @TableGenerator(
            name = "file_name_id_gen",
            table = "id_generator",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "file_name_id", // Explicit segment value
            allocationSize = 5
    )
    @Schema(description = "Unique identifier for the file name", example = "1")
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    @Schema(description = "The unique name of the file", example = "example.txt", maxLength = 200)
    private String name;

    public FileName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}