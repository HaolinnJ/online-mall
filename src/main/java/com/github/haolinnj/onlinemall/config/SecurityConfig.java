package com.github.haolinnj.onlinemall.config;

import com.github.haolinnj.onlinemall.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;
    @Autowired(required = false)
    private DynamicSecurityFilter dynamicSecurityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    // 关闭CSRF，因为是无状态的JWT认证
                    .csrf(csrf -> csrf.disable())
                    // 不使用Session，改为无状态认证
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    // 配置请求授权
                    .authorizeHttpRequests(authorize -> {
                        // 不需要保护的资源路径允许访问
                        for (String url : ignoreUrlsConfig.getUrls()) {
                            authorize.requestMatchers(url).permitAll();
                        }
                        // 允许跨域请求的OPTIONS请求
                        authorize.requestMatchers(HttpMethod.OPTIONS).permitAll();
                        // 任何其他请求都需要身份认证
                        authorize.anyRequest().authenticated();
                    })
                    // 自定义权限拒绝处理类和认证入口点
                    .exceptionHandling(exceptions -> exceptions
                            .accessDeniedHandler(restfulAccessDeniedHandler) // 权限不足时的处理
                            .authenticationEntryPoint(restAuthenticationEntryPoint) // 未认证时的处理
                    );

            // 添加JWT认证过滤器在UsernamePasswordAuthenticationFilter之前
            httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

            // 如果有动态权限配置，添加动态权限校验过滤器
            if (dynamicSecurityService != null) {
                httpSecurity.addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
            }

            return httpSecurity.build();
        }
    }
//        http.csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> {
//                    for (String url : ignoreUrlsConfig.getUrls()) {
//                        auth.requestMatchers(url).permitAll();
//                    }
//                    auth.requestMatchers(HttpMethod.OPTIONS).permitAll();
//                    auth.anyRequest().authenticated();
//                })
//                .exceptionHandling(ex -> ex
//                        .accessDeniedHandler(restfulAccessDeniedHandler)
//                        .authenticationEntryPoint(restAuthenticationEntryPoint)
//                )
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        //有动态权限配置时添加动态权限校验过滤器
//        if(dynamicSecurityService!=null){
//            registry.and().addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
//        }
//        http.headers(headers -> headers
//                .cacheControl(cache -> cache.disable())); //禁止浏览器缓存响应，避免安全数据被前端缓存
//        return http.build();
//    }

//        //去除所有安全配置
//        httpSecurity.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize->authorize.anyRequest().permitAll());
//        return httpSecurity.build();
//    }}

