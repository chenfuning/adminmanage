package com.ning.adminmanage.service.impl;

import com.ning.adminmanage.base.result.ResponseCode;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.RoleDao;
import com.ning.adminmanage.dao.RolePermissionDao;
import com.ning.adminmanage.dao.RoleUserDao;
import com.ning.adminmanage.dto.RoleDto;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.model.SysRoleUser;
import com.ning.adminmanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private RoleUserDao roleUserDao;

    @Override
    public Results<SysRole> getAllRoles() {
        return Results.success(50,roleDao.getAllRoles());
    }

    @Override
    public Results<SysRole> getAllRolesByPage(Integer offset, Integer limit) {
        return Results.success(roleDao.countAllRoles().intValue(),roleDao.getAllRoleByPage(offset,limit));
    }

    @Override
    public Results<SysRole> getRolesByFuzzyRoleName(String rolename, Integer offset, Integer limit) {
        return  Results.success(roleDao.getRoleByFuzzyRolename(rolename).intValue(),roleDao.getRoleByFuzzyRolenameByPage(rolename,offset,limit));
    }

    @Override
    public Results save(RoleDto roleDto) {
        //保存角色
        roleDao.saveRole(roleDto);
        //保存角色对应的权限，这里的permissionid是前端获取到的存放在数组中
        List<Long> permissionIds=roleDto.getPermissionIds();
        //移除0，permission id 是从1开始
        permissionIds.remove(0L);
        if(!CollectionUtils.isEmpty(permissionIds)){
            //这里使用批量插入，mapper里面使用foreach标签
            rolePermissionDao.save(roleDto.getId(),permissionIds);
        }
        return Results.success();
    }

    @Override
    public SysRole getRoleById(Integer id) {
        return roleDao.getById(id);
    }

    @Override
    public Results update(RoleDto roleDto) {
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        //1、更新角色权限之前要删除该角色之前的所有权限
        rolePermissionDao.deleteRolePermission(roleDto.getId());

        //2、判断该角色是否有赋予权限值，有就添加"
        if (!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.save(roleDto.getId(), permissionIds);
        }

        //3、更新角色表
        int countData = roleDao.update(roleDto);
        if(countData > 0){
            return Results.success();
        }else{
            return Results.failure();
        }
    }

    @Override
    public Results<SysRole> delete(Integer id) {
        List<SysRoleUser> datas = roleUserDao.listAllSysRoleUserByRoleId(id);
        if(datas.size() <= 0){
            roleDao.delete(id);
            return Results.success();
        }
        return Results.failure(ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getCode(),ResponseCode.USERNAME_REPEAT.USER_ROLE_NO_CLEAR.getMessage());
    }


}
