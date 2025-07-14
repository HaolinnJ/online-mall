package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.domain.AdminUserDetails;
import com.github.haolinnj.onlinemall.domain.UmsResource;
import com.github.haolinnj.onlinemall.dto.UmsAdminParam;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;

import java.util.List;

//后台用户管理
public interface IUmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUserDetails getAdminByUsername(String username);

    /**
     * registration
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 获取所有权限列表
     */
    List<UmsResource> getResourceList();

    /**
     * 用户名密码登录
     */
    String login(String username, String Password);


}
