package com.autotrek.instantauto.repositories;

import com.autotrek.instantauto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for User entities.
 *
 * @author Joe C. McPherson
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Return a user by their username.
     *
     * @param username The username.
     * @return
     */
    public User getUserByUsername(String username);
}
