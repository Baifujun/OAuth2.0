package com.gzhtdq.daas.security.service;

import com.gzhtdq.daas.security.domai.User;

public interface UserService {
    User getUserByUsername(String username);

    int insertUser(User user);
}
