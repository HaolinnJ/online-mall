package com.github.haolinnj.onlinemall.config;

import com.github.haolinnj.onlinemall.domain.AdminUserDetails;
import com.github.haolinnj.onlinemall.service.IUmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class MallSecurityConfig {

    @Autowired
    private IUmsAdminService umsAdminService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            AdminUserDetails admin = umsAdminService.getAdminByUsername(username);
            if(admin != null){
                return  admin;
            }
            throw new UsernameNotFoundException("Username or password wrong");
        };
    }
}
