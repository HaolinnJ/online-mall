package com.github.haolinnj.onlinemall.service.impl;

import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UmsRoleServiceImpl implements IUmsRoleService {

    @Override
    public int create(UmsRole role) {
        return 0;
    }

    @Override
    public int update(Long id, UmsRole role) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<UmsRole> list() {
        return List.of();
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return List.of();
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return List.of();
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return List.of();
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return List.of();
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }
}
