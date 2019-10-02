package com.jarven.example.security.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author hejiawen <br>
 * @version 1.0<br>
 * @since V1.0<br>
 */
public class RoleAuthority implements GrantedAuthority {

    private String roleName;

    public RoleAuthority(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
