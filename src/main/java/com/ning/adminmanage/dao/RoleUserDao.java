package com.ning.adminmanage.dao;

import com.ning.adminmanage.model.SysRoleUser;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleUserDao {


    @Insert("insert into sys_role_user(userId, roleId) values(#{userId}, #{roleId})")
    void save(SysRoleUser sysRoleUser);

    @Select("select * from sys_role_user t where t.userId=#{userId}")
    SysRoleUser getSysRoleUserByUserId(Integer userId);

    int updateRoleUser(SysRoleUser sysRoleUser);
    @Delete("delete from sys_role_user where userId=#{userId}")
    int deleteRoleUserByUserId(int userId);
    @Select("select * from sys_role_user t where t.roleId = #{roleId}")
    List<SysRoleUser> listAllSysRoleUserByRoleId(Integer roleId);
}
