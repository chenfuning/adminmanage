package com.ning.adminmanage.dao;


import com.ning.adminmanage.model.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Select("select * from sys_permission t")
    List<SysPermission> findAll();
    @Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
    List<SysPermission> listByRoleId(Integer roleId);
}
