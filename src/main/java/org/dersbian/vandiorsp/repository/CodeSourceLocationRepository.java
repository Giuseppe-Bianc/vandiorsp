package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsp.model.CodeSourceLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeSourceLocationRepository extends JpaRepository<CodeSourceLocation, Long> {

    // Find by line and column
    Optional<CodeSourceLocation> findByLineAndColumn(int line, int column);
}
