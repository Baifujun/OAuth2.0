package com.gzhtdq.daas.security.service.impl;

import com.gzhtdq.daas.security.domai.User;
import com.gzhtdq.daas.security.mapper.UserDao;
import com.gzhtdq.daas.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }
}
