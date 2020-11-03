package com.gzhtdq.daas.security.service.impl;

import com.gzhtdq.daas.security.domai.User;
import com.gzhtdq.daas.security.mapper.PermDao;
import com.gzhtdq.daas.security.mapper.RoleDao;
import com.gzhtdq.daas.security.mapper.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermDao permDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("查询用户信息和角色权限信息ing");
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            List<String> roles = roleDao.getUserRoleByUserId(user.getId());
            List<String> perms = permDao.getUserPermByUserId(user.getId());
            log.info(roles.toString());
            log.info(perms.toString());
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (String role : roles) {
                if (!StringUtils.isEmpty(role)) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                    authorities.add(authority);
                }
            }
            for (String perm : perms) {
                if (!StringUtils.isEmpty(perm)) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(perm);
                    authorities.add(authority);
                }
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(username + " not found");
        }
    }

}
