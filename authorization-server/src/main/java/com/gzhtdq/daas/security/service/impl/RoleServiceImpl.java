package com.gzhtdq.daas.security.service.impl;

import com.gzhtdq.daas.security.mapper.RoleDao;
import com.gzhtdq.daas.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> getUserRoleByUserId(Integer userId) {
        return roleDao.getUserRoleByUserId(userId);
    }
}
