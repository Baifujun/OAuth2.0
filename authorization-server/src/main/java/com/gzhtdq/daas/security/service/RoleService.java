package com.gzhtdq.daas.security.service;

import java.util.List;

public interface RoleService {
    List<String> getUserRoleByUserId(Integer userId);
}
