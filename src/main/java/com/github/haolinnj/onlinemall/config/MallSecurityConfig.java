package com.github.haolinnj.onlinemall.config;

import com.github.haolinnj.onlinemall.component.DynamicSecurityService;
import com.github.haolinnj.onlinemall.domain.AdminUserDetails;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import com.github.haolinnj.onlinemall.service.IUmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MallSecurityConfig {

    @Autowired
    private IUmsAdminService umsAdminService;
    @Autowired
    private IUmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> umsAdminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = resourceService.listAll();
                for (UmsResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
