package com.lqs.mall.filter;

import com.lqs.mall.common.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT授权过滤器
 * Created by 刘千山 on 2023/6/9/009-20:19
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        获取请求Header和token
        String Header = request.getHeader(tokenHeader);
        if(Header == null){
            filterChain.doFilter(request, response);
            return;
        }
        String token = Header.substring(tokenHeader.length());
        LOGGER.info("token:{}",token);
//        如果存在token,解析token
        if (Header != null && Header.startsWith(tokenHead)) {
            // 获取用户
            String userName = jwtTokenUtil.getUserNameFromToken(token);
            LOGGER.info("username:{}", userName);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //        存入SecurityContextHolder
                //TODO 获取权限信息
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
