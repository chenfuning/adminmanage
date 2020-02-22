package com.ning.adminmanage.service.impl;

import com.ning.adminmanage.dao.PermissionDao;
import com.ning.adminmanage.dto.LoginUser;
import com.ning.adminmanage.model.SysPermission;
import com.ning.adminmanage.model.SysUser;
import com.ning.adminmanage.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser=userService.getUser(username);
        //判断该用户是否可用
        if (sysUser == null) {
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (sysUser.getStatus() == SysUser.Status.LOCKED) {
            throw new LockedException("用户被锁定,请联系管理员");
        } else if (sysUser.getStatus() == SysUser.Status.DISABLED) {
            throw new DisabledException("用户已作废");
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        //获取并赋给用户权限
        List<SysPermission> permissions = permissionDao.listByUserId(sysUser.getId());
        loginUser.setPermissions(permissions);

        return loginUser;

    }
}
