package com.ning.adminmanage.service.impl;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.RoleUserDao;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.model.SysRoleUser;
import com.ning.adminmanage.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserSerivceImpl implements RoleUserService {
    @Autowired
    private RoleUserDao roleUserDao;
    @Override
    public Results getSysRoleUserByUserId(Integer userId) {
         SysRoleUser sysRoleUser=roleUserDao.getSysRoleUserByUserId(userId);
        if(sysRoleUser!=null){
            return  Results.success(sysRoleUser);
        }else{
            return Results.success();
        }
    }
}
