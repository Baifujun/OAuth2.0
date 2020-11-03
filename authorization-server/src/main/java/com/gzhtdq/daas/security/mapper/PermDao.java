package com.gzhtdq.daas.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermDao {
    List<String> getUserPermByUserId(Integer userId);
}
