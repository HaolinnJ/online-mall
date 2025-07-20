package com.github.haolinnj.onlinemall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.haolinnj.onlinemall.mbg.mapper.UmsRoleMapper;
import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import com.github.haolinnj.onlinemall.mbg.model.UmsRoleExample;
import com.github.haolinnj.onlinemall.service.IUmsAdminCacheService;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements IUmsRoleService {
    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private IUmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return roleMapper.insert(role);
    }

    @Override
    public int update(Long id, UmsRole role) {
        role.setId(id);
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        int count = roleMapper.deleteByExample(example);
        adminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UmsRole> list() {
        return roleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if(!StrUtil.isEmpty(keyword)){
            example.createCriteria().andNameLike("%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
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
