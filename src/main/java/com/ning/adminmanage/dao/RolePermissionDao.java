package com.ning.adminmanage.dao;

import com.ning.adminmanage.model.RolePermission;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionDao {

    int save(@Param("roleId") Integer id, @Param("permissionIds") List<Long> permissionIds);
    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void deleteRolePermission(Integer id);
}
