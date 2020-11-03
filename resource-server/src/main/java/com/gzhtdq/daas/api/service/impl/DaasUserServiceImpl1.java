package com.gzhtdq.daas.api.service.impl;

import com.gzhtdq.daas.api.domain.ds1.DaasUser1;
import com.gzhtdq.daas.api.mapper.ds1.DaasUserMapper1;
import com.gzhtdq.daas.api.service.DaasUserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaasUserServiceImpl1 implements DaasUserService1 {

    @Autowired
    private DaasUserMapper1 daasUserMapper1;

    @Override
    public List<DaasUser1> list() {
        return daasUserMapper1.list();
    }
}
