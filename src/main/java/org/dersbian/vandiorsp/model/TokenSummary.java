package org.dersbian.vandiorsp.model;

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
@Table(name = "token_summary")
public class TokenSummary {
    @Id
    /*@GeneratedValue(strategy = GenerationType.TABLE, generator = "token_summary_id_gen")
    @TableGenerator(
            name = "token_summary_id_gen",
            table = "id_generator",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "token_summary_id", // Explicit segment value
            allocationSize = 150
    )*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_summary_seq_gen")
    @SequenceGenerator(
            name = "token_summary_seq_gen",
            sequenceName = "token_summary_seq",
            allocationSize = 150
    )
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TokenType type;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "file_name_id", nullable = false)
    private FileName fileName;

    @Column(name = "token_count", nullable = false)
    private Long tokenCount;
}