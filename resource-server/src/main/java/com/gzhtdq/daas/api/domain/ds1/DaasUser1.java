package com.gzhtdq.daas.api.domain.ds1;

import lombok.Data;

@Data
public class DaasUser1 {
    private Integer id;
    private String username;
    private String password;
    private Integer accountExpired;
    private Integer accountLocked;
    private Integer credentialsExpired;
    private Integer enabled;
}
