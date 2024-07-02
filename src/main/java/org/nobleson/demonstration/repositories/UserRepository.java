package org.nobleson.demonstration.repositories;

import org.nobleson.demonstration.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> getAppUserByUserID(String userID);
}
