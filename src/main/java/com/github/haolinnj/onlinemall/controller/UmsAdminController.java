package com.github.haolinnj.onlinemall.controller;

import cn.hutool.core.collection.CollUtil;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.dto.UmsAdminLoginParam;
import com.github.haolinnj.onlinemall.dto.UmsAdminParam;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Operation(description = "user login and return token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("Username or password incorrect");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @Operation(description = "refresh token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if(refreshToken == null){
            return CommonResult.failed("token is out of time");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @Operation(description = "get current login user info")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult getAdminInfo(Principal principal){
        if(principal == null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return CommonResult.success(data);
    }

    @Operation(description = "logout function")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout(Principal principal){
        adminService.logout(principal.getName());
        return CommonResult.success(null);
    }


}
