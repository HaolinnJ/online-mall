package com.github.haolinnj.onlinemall.component;

import com.github.haolinnj.onlinemall.common.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException{
        //从请求头中提取Token
        String authHeader = request.getHeader(this.tokenHeader);
        //检查请求头是否以特定前缀开头
        if (authHeader != null && authHeader.startsWith(this.tokenHead)){
            // get the part after "Bearer"
            String authToken = authHeader.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            LOGGER.info("checking username:{}",username);
            //如果用户名存在，且当前securityContext中还没有设置认证对象（防止重复认证）
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //加载用户信息
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //验证Token是否有效
                if (jwtTokenUtil.validateToken(authToken,userDetails)){
                    //创建AuthenticationToken对象作为SpringSecurity的认证凭证
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    LOGGER.info("authenticated user:{}",username);
                    //将认证对象设置到当前线程的SecurityContext中
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //将请求传递给下一个过滤器或处理器
        chain.doFilter(request,response);
    }
}
