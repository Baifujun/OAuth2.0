package com.gzhtdq.daas.api.service.impl;

import com.gzhtdq.daas.api.mapper.ds2.DaasUserMapper2;
import com.gzhtdq.daas.api.service.DaasUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaasUserServiceImpl2 implements DaasUserService2 {

    @Autowired
    private DaasUserMapper2 userMapper2;

    @Override
    public String selectUsername(Integer id) {
        return userMapper2.selectUsername(id);
    }
}
