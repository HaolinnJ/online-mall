package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.domain.UmsResource;
import com.github.haolinnj.onlinemall.dto.UmsAdminParam;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//后台用户管理
@RestController
@Tag(name = "UmsAdminController", description = "User Management")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private IUmsAdminService adminService;
    @Autowired
    private IUmsRoleService roleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Operation(description = "user registration")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register (@Validated @RequestBody UmsAdminParam umsAdminParam){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(umsAdmin == null){
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @Operation(description = "login function")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestParam String username, @RequestParam String password) {
        String token = adminService.login(username, password);
        if (token == null) {
            return CommonResult.validateFailed("Username or password incorrect");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @Operation(description = "loin and return resourcelist")
    @RequestMapping(value = "/resourceList", method = RequestMethod.POST)
    public CommonResult<List<UmsResource>> resourceList() {
        List<UmsResource> resourceList = adminService.getResourceList();
        return CommonResult.success(resourceList);
    }
}
