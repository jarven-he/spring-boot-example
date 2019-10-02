package com.jarven.example.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarven.example.security.domain.SecurityUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，在这个方法里生成token。
 *
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws IOException {
        var user = new ObjectMapper().readValue(req.getInputStream(), SecurityUser.class);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, user));
        } catch (Exception e) {
            log.error("认证发生错误:{}", e.getMessage(), e);
            var jsonObject = new JSONObject();
            jsonObject.put("success", false);
            jsonObject.put("message", "账号/密码不正确");
            res.getWriter().write(jsonObject.toJSONString());
            res.flushBuffer();
        }
        return authentication;
    }

    /**
     * 用户发起getToken请求，这个方法会被调用，在这个方法里生成token
     *
     * @param req   http请求
     * @param res   http请求
     * @param chain 认证信息
     * @param auth  角色
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        var user = (SecurityUser) auth.getPrincipal();
        var token = Jwts.builder()
                .claim("authorities", auth.getAuthorities())
                .setSubject(JSONObject.toJSONString(user))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
                //采用HS512算法
                .signWith(SignatureAlgorithm.HS512, "4bf24b6be4a9742f4103dbc6343d")
                .compact();
        var jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("token", "Bearer " + token);
        res.getWriter().write(jsonObject.toJSONString());
        res.addHeader("Authorization", "Bearer " + token);
        res.flushBuffer();
    }
}
