package com.jarven.example.security.filter;

import com.jarven.example.security.domain.RoleAuthority;
import com.jarven.example.security.domain.RoleEnum;
import com.jarven.example.security.domain.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;


/**
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UrlFilter urlFilter;

    public CustomAuthenticationProvider(UrlFilter urlFilter) {
        this.urlFilter = urlFilter;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        // 获取认证的用户名 & 密码
        var user = (SecurityUser) authentication.getPrincipal();
        if (urlFilter.identifyCallBack(user)) {
            return new UsernamePasswordAuthenticationToken(user, user, Collections.singletonList(new RoleAuthority(RoleEnum.getRole(user.getUserType()))));
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
