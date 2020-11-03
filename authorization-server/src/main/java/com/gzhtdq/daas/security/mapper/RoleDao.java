package com.gzhtdq.daas.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
    List<String> getUserRoleByUserId(Integer userId);
}
