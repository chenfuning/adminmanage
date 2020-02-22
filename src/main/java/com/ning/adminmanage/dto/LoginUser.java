package com.ning.adminmanage.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ning.adminmanage.model.SysPermission;
import com.ning.adminmanage.model.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class LoginUser extends SysUser implements UserDetails {
        private List<SysPermission> permissions;
    private String token;
    /** 登陆时间戳（毫秒） */
    private Long loginTime;
    /** 过期时间戳 */
    private Long expireTime;
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return getStatus()!=Status.LOCKED;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
