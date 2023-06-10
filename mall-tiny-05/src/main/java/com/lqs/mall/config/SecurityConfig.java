package com.lqs.mall.config;

import cn.hutool.json.JSONUtil;
import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 刘千山 on 2023/6/8/008-11:06
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //    密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/admin/login").anonymous()
                // 设置Swagger3匿名访问
                .antMatchers("/swagger-ui.html",
                        "/swagger-ui/*",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()        // 前后端分离 关闭
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用session
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(this::accessDeniedHandler)
                .authenticationEntryPoint(this::authenticationEntryPoint)
                .and()
                .build();
    }


    /**
     * 没有权限时返回
     */
    public void accessDeniedHandler(HttpServletRequest request,
                                    HttpServletResponse response,
                                    AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(e.getMessage())));
        response.getWriter().flush();
    }

    /**
     * 未登录或者token过期时
     */
    public void authenticationEntryPoint(HttpServletRequest request,
                                         HttpServletResponse response,
                                         AuthenticationException e) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(e.getMessage())));
        response.getWriter().flush();
    }


}
