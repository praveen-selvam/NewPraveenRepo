package com.autotrek.instantauto.services.impl;

import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.repositories.UserRepository;
import com.autotrek.instantauto.services.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Joe C. McPherson
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        LOGGER.debug("Finding user by id: {}", id);
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
}
