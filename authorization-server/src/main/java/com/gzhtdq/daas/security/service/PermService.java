package com.gzhtdq.daas.security.service;

import java.util.List;

public interface PermService {
    List<String> getUserPermByUserId(Integer userId);
}
