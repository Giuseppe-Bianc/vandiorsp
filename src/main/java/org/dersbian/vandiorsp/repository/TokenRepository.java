package org.dersbian.vandiorsp.repository;

import org.dersbian.vandiorsq.models.Token;
import org.dersbian.vandiorsq.models.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    // Find tokens by their type
    List<Token> findByType(TokenType type);

    // Find tokens where the type matches any in a given list
    List<Token> findByTypeIn(List<TokenType> types);

    // Find tokens with a specific value
    List<Token> findByValue(String value);
}
