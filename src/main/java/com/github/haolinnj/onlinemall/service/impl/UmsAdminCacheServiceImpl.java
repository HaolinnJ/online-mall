package com.github.haolinnj.onlinemall.service.impl;

import com.github.haolinnj.onlinemall.dao.UmsAdminRoleRelationDao;
import com.github.haolinnj.onlinemall.mbg.mapper.UmsAdminRoleRelationMapper;
import com.github.haolinnj.onlinemall.mbg.model.UmsAdmin;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.service.IRedisService;
import com.github.haolinnj.onlinemall.service.IUmsAdminCacheService;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UmsAdminCacheServiceImpl implements IUmsAdminCacheService {
    @Autowired
    private IUmsAdminService adminService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getItem(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }

    @Override
    public void delResourceListByRole(Long roleId) {

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void delResourceListByResource(Long resourceId) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        return null;
    }

    @Override
    public void setAdmin(UmsAdmin admin) {

    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return List.of();
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
