package com.gzhtdq.daas.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    // 拥有admin角色可访问
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    // 拥有admin或者normal角色都可访问
    @PreAuthorize("hasAnyRole('admin, normal')")
    @GetMapping("/normal")
    public String normal() {
        return "normal";
    }

    // 拥有user:all权限可访问
    @PreAuthorize("hasAuthority('user:all')")
    @GetMapping("/all")
    public String all() {
        return "all";
    }

    // 拥有user:all或者user:view权限都可访问
    @PreAuthorize("hasAnyAuthority('user:all', 'user:view')")
    @GetMapping("/view")
    public String view() {
        return "view";
    }
}
