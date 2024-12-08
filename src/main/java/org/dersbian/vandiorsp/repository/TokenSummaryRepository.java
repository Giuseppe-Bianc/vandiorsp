package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsq.models.TokenSummary;
import org.dersbian.vandiorsq.models.dto.TokenSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TokenSummaryRepository extends JpaRepository<TokenSummary, Long> {
    @Query(value = "SELECT new org.dersbian.vandiorsq.models.dto.TokenSummaryDto(COUNT(t.id), f.id, t.type) " +
            "FROM Token t " +
            "JOIN CodeSourceLocation csl ON t.sourceLocation.id = csl.id " +
            "JOIN FileName f ON csl.fileName.id = f.id " +
            "GROUP BY t.type, f.id " +
            "ORDER BY t.type, f.id")
    List<TokenSummaryDto> findTokenSummaries();
}
