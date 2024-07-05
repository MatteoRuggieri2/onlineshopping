package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserFunctions {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean login(String email, String password) {
        return false;
    }

    @Override
    public boolean ifUserExist(int userId) {
        return false;
    }

}
