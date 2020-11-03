package com.gzhtdq.daas.api.controller;

import com.gzhtdq.daas.api.domain.ds1.DaasUser1;
import com.gzhtdq.daas.api.service.DaasUserService1;
import com.gzhtdq.daas.api.service.DaasUserService2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "oauth2认证测试接口")
public class TestController extends BaseController {
    @Autowired
    private DaasUserService1 daasUserService1;
    @Autowired
    private DaasUserService2 daasUserService2;

    @GetMapping("/test/ds1/{id}")
    @ApiOperation("接口test1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id值", defaultValue = "1")
    })
    //@PreAuthorize("hasRole('ROLE_TEST_ADMIN')")
    public List<DaasUser1> test1(@PathVariable Integer id) {
        return daasUserService1.list();
    }

    @GetMapping("/test/ds2/{id}")
    @ApiOperation("接口test2")
    @ApiImplicitParam(name = "id", value = "id值", defaultValue = "1", required = true)
    //@PreAuthorize("hasRole('ROLE_TEST_ADMIN')")
    public String test2(@PathVariable Integer id) {
        return daasUserService2.selectUsername(id);
    }
}
