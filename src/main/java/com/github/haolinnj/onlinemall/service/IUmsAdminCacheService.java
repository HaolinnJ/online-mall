package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;

import java.util.List;

public interface IUmsAdminCacheService {
    /**
     * delete user cache
     */
    void delAdmin(Long adminId);

    /**
     * delete user resource list cache
     */
    void delResourceList(Long adminId);

    /**
     * when the role info changed, delete related user cache
     */
    void delResourceListByRole(Long roleId);

    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * when the resource info changed, delete related user cache
     */
    void delResourceListByResource (Long resourceId);

    /**
     * get user cache info
     */
    UmsAdmin getAdmin(String username);

    /**
     * set user cache info
     */
    void setAdmin(UmsAdmin admin);

    /**
     * get resource list
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * set resource list
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);
}
