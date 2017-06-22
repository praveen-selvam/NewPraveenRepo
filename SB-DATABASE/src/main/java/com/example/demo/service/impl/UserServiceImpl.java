package com.example.demo.service.impl;

import com.example.demo.exception.CustomNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);

        return users;
    }

    @Override
    public User getUser(String id) {

        User user = userRepository.findOne(id);

        if (null == user) {
            throw new CustomNotFoundException("404 Error" + " " + "You Entered Wrong ID " + id);
        }

        return user;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(String id, User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(id);
    }

}
