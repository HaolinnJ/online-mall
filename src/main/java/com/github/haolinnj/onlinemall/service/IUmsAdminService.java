package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.domain.AdminUserDetails;
import com.github.haolinnj.onlinemall.dto.UmsAdminParam;
import com.github.haolinnj.onlinemall.dto.UpdateAdminPasswordParam;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//后台用户管理
public interface IUmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * registration
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 用户名密码登录
     */
    String login(String username, String Password);

    /**
     * refresh token
     */
    String refreshToken (String oldToken);

    /**
     * get user by user id
     */
    UmsAdmin getItem(Long id);

    /**
     * find user by username or nickname
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * edit user info
     */
    int update (Long id, UmsAdmin admin);

    /**
     * delete user
     */
    int delete(Long id);

    /**
     * update user role relation
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * get related role of user
     */
    List<UmsRole> getRoleList (Long adminId);

    /**
     * get related resource of user
     */
    List<UmsResource> getResourceList (Long adminId);

    /**
     * change password
     */
    int updatePassword (UpdateAdminPasswordParam updatePasswordParam);

    /**
     * get user info
     */
    UserDetails loadUserByUsername (String username);

    /**
     * get cache service
     */
    IUmsAdminCacheService getCacheService();

    /**
     * logout function
     */
    void logout(String username);
}
