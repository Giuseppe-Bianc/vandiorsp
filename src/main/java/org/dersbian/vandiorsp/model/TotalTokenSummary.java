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
@Table(name = "total_token_summary")
public class TotalTokenSummary {
    @Id
    /*@GeneratedValue(strategy = GenerationType.TABLE, generator = "total_token_summary_id_gen")
    @TableGenerator(
            name = "total_token_summary_id_gen",
            table = "id_generator",
            pkColumnName = "id_name",
            valueColumnName = "id_value",
            pkColumnValue = "total_token_summary_id", // Explicit segment value
            allocationSize = 150
    )*/
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "total_token_summary_seq_gen")
    @SequenceGenerator(
            name = "total_token_summary_seq_gen",
            sequenceName = "total_token_summary_seq",
            allocationSize = 150
    )
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TokenType type;

    @Column(name = "total_token_count", nullable = false)
    private Long totalTokenCount;

    public TotalTokenSummary(TokenType type, Long totalTokenCount) {
        this.type = type;
        this.totalTokenCount = totalTokenCount;
    }
}