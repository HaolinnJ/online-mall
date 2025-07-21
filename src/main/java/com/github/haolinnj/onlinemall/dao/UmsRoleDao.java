package com.github.haolinnj.onlinemall.dao;

import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleDao {
    /**
     * get menu based on admin id
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * get menu by role id
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * get resource list by role id
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
