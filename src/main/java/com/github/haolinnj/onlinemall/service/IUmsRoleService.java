package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * role management service
 */
public interface IUmsRoleService {
    /**
     * add role
     */
    int create(UmsRole role);

    /**
     * update role info
     */
    int update(Long id, UmsRole role);

    /**
     * delete roles
     */
    int delete(List<Long> ids);

    /**
     * get role list
     */
    List<UmsRole> list();

    /**
     * get role list by page
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * get menu by adminId
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * get role related menu
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * get role related resources
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * assign menu to role
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * assign resource to role
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
