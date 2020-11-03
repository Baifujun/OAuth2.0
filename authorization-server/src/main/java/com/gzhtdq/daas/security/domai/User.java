package com.gzhtdq.daas.security.domai;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer accountExpired;
    private Integer accountLocked;
    private Integer credentialsExpired;
    private Integer enabled;
}
