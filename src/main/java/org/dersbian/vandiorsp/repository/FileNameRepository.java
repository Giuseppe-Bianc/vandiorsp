package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsp.model.FileName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileNameRepository extends JpaRepository<FileName, Long> {
    // Additional query methods (if needed) can be defined here
    @Query("SELECT fn FROM FileName fn WHERE fn.name = :name")
    Optional<FileName> findByName(String name);
}
