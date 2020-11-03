package com.gzhtdq.daas.security.mapper;

import com.gzhtdq.daas.security.domai.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserByUsername(String username);

    int insertUser(User user);
}
