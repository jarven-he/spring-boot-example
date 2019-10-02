package com.jarven.example.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarven.example.security.domain.RoleAuthority;
import com.jarven.example.security.domain.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final String BEARER = "Bearer ";

    private final String OPTIONS = "OPTIONS";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //解决认证请求跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        String header = request.getHeader("Authorization");
        if (StringUtils.isEmpty(header) || !header.startsWith(BEARER)) {
            if (request.getMethod().equals(OPTIONS)) {
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
                response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
                response.setHeader("Access-Control-Expose-Headers", "*");
                response.setHeader("Access-Control-Allow-Headers", "cache-control,content-type,hash-referer,x-requested-with,Authorization");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                return;
            }
            chain.doFilter(request, response);
            return;
        }
        try {
            var authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.sendError(403, "Access Denied");
            response.getWriter().flush();
            return;
        }
        chain.doFilter(request, response);
    }

    /**
     * 安全认证
     *
     * @param request 请求
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader("Authorization");
        if (token != null) {
            // 解析 Token
            Claims claims = Jwts.parser()
                    .setSigningKey("4bf24b6be4a9742f4103dbc6343d")
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            //得到用户
            var user = new ObjectMapper().readValue(claims.getSubject(), SecurityUser.class);

            List list = claims.get("authorities", List.class);
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (Object obj : list) {
                LinkedHashMap map = (LinkedHashMap) obj;
                authorities.add(new RoleAuthority(map.get("authority").toString()));
            }
            setSession(request, user);
            return new UsernamePasswordAuthenticationToken(user, user, authorities);
        }
        return null;
    }

    private void setSession(HttpServletRequest req, SecurityUser user) {
        req.setAttribute("userId", user.getUserId());
        req.setAttribute("userType", user.getUserType());
        req.setAttribute("openId", user.getOpenId());
        if (user.getExtension() != null) {
            user.getExtension().keySet().forEach(key ->
                    req.setAttribute(key, user.getExtension().get(key))
            );
        }
    }
}
