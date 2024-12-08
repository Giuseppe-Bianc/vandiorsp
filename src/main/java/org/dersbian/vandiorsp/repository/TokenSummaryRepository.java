package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsp.model.TokenSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenSummaryRepository extends JpaRepository<TokenSummary, Long> {

}
