package com.ning.adminmanage.service.impl;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.RoleUserDao;
import com.ning.adminmanage.dao.UserDao;
import com.ning.adminmanage.dto.UserDto;
import com.ning.adminmanage.model.SysRoleUser;
import com.ning.adminmanage.model.SysUser;
import com.ning.adminmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleUserDao roleUserDao;
    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
        return Results.success(userDao.countAllUsers().intValue(),userDao.getAllUserByPage(offset,limit));
    }

    @Override
    public Results save(SysUser sysUser, Integer role) {
        if(role!=null){
            //存储user表
            userDao.save(sysUser);
            //存储RoleUser表
            SysRoleUser sysRoleUser=new SysRoleUser();
            sysRoleUser.setRoleId(role);
            sysRoleUser.setUserId(sysUser.getId().intValue());
            roleUserDao.save(sysRoleUser);
            return  Results.success();
        }
        return Results.failure();
    }
}
