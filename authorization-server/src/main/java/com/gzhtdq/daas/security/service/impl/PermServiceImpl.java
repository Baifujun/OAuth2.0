package com.gzhtdq.daas.security.service.impl;

import com.gzhtdq.daas.security.mapper.PermDao;
import com.gzhtdq.daas.security.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {
    @Autowired
    private PermDao permDao;

    @Override
    public List<String> getUserPermByUserId(Integer userId) {
        return permDao.getUserPermByUserId(userId);
    }
}
