package com.autotrek.instantauto.services;

import com.autotrek.instantauto.domain.User;
import java.util.List;

/**
 * A service definition for users.
 *
 * @author Joe C. McPherson
 */
public interface UserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByUsername(String username);
}
