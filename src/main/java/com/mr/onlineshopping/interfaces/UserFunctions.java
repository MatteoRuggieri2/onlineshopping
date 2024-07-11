package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.User;

import java.util.Optional;

public interface UserFunctions {
    Optional<User> getUserById(int userId);
    Optional<User> getUserByEmail(String email);
    boolean login(String email, String password);
    boolean ifUserExists(int userId);
}
