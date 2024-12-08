package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsq.models.TotalTokenSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalTokenSummaryRepository extends JpaRepository<TotalTokenSummary, Long> {
    @Query(value = "SELECT new org.dersbian.vandiorsq.models.TotalTokenSummary(ts.type, SUM(ts.tokenCount)) " +
            "FROM TokenSummary ts " +
            "GROUP BY ts.type " +
            "ORDER BY ts.type")
    List<TotalTokenSummary> findTokenSummaries();
}
