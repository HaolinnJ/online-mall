package com.github.haolinnj.onlinemall.controller;

import cn.hutool.core.collection.CollUtil;
import com.github.haolinnj.onlinemall.common.api.CommonPage;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.dto.UmsAdminLoginParam;
import com.github.haolinnj.onlinemall.dto.UmsAdminParam;
import com.github.haolinnj.onlinemall.dto.UpdateAdminPasswordParam;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.simpleframework.xml.Path;
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

    @Operation(description = "get user list by username")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public CommonResult <CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @Operation(description = "get user info")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsAdmin> getItem (@PathVariable Long id){
        UmsAdmin admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @Operation(description = "change user info")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin){
        int count = adminService.update(id, admin);
        if (count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "change user password")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam){
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0){
            return CommonResult.success(status);
        } else if (status == -1){
            return CommonResult.failed("Invalid parameter");
        } else if (status == -2) {
        return CommonResult.failed("Could not find user");
        } else if (status == -3) {
            return CommonResult.failed("Wrong old password");
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "delete user info")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public  CommonResult delete(@PathVariable Long id){
        int count = adminService.delete(id);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "update account status")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus (@PathVariable Long id, @RequestParam(value = "status") Integer status){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = adminService.update(id, umsAdmin);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "assign role to user")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds){
        int count = adminService.updateRole(adminId, roleIds);
        if(count >= 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "get roles of user")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    public CommonResult<List<UmsRole>> getRoleList (@PathVariable Long adminId){
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
