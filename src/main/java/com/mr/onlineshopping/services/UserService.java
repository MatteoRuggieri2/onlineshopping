package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserFunctions {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).isPresent();
    }

    @Override
    public boolean ifUserExists(int userId) {
        return userRepository.existsById(userId);
    }

}
