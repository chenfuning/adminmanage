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

    @Override
    public SysUser getUserByPage(String phone) {
        return userDao.getUserByPage(phone);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {
        if(roleId!=null) {
            //修改user表
            userDao.updateUser(userDto);
            //修改roleuser表或者保存（看是否设置了角色）
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setUserId(userDto.getId().intValue());
            sysRoleUser.setRoleId(roleId);
            if (roleUserDao.getSysRoleUserByUserId(userDto.getId().intValue()) != null) {
                //角色表有记录就采用更新的方法
                roleUserDao.updateRoleUser(sysRoleUser);
            } else {
                roleUserDao.save(sysRoleUser);
            }
            return Results.success();
        }else{
        return Results.failure();
        }
    }

    @Override
    public int deleteUser(Long id) {
        //删除用户表
        int delResult =userDao.deleteUser(id.intValue());
        //删除roleuser表
        roleUserDao.deleteRoleUserByUserId(id.intValue());
        return  delResult;
    }

    @Override
    public Results<SysUser> getUserByFuzzyUserName(String username,Integer offset, Integer limit) {
        //提供中的条数，和具体的数据
        return Results.success(userDao.getUserByFuzzyUsername(username).intValue(),userDao.getUserByFuzzyUsernameByPage(username,offset,limit));
    }
}
