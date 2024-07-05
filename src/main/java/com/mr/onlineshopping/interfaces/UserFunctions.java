package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.User;

public interface UserFunctions {
    User getUserById(int userId);
    User getUserByEmail(String email);
    boolean login(String email, String password);
    boolean ifUserExist(int userId);
}
