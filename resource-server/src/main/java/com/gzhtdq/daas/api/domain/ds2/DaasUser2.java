package com.gzhtdq.daas.api.domain.ds2;

import lombok.Data;

@Data
public class DaasUser2 {
    private Integer id;
    private String username;
    private String password;
    private Integer accountExpired;
    private Integer accountLocked;
    private Integer credentialsExpired;
    private Integer enabled;
}
