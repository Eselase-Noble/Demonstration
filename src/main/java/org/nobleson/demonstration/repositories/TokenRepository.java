package org.nobleson.demonstration.repositories;

import org.nobleson.demonstration.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
            select t from Token t inner join AppUser u\s 
            on t.user.userID = u.userID\s 
            where u.userID = :userID and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenUser(String userID);

    Optional<Token> findByToken(String token);
}
