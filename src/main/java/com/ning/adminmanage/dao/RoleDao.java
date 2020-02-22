package com.ning.adminmanage.dao;


import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dto.RoleDto;
import com.ning.adminmanage.model.SysRole;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();

    @Select("select count(*) from sys_role t")
    Long countAllRoles();
    @Select("select * from sys_role t order by t.id limit #{startPosition},#{limit}")
    List<SysRole> getAllRoleByPage(@Param("startPosition")Integer offset, Integer limit);

    @Select("select count(*) from sys_role t where t.name like '%${rolename}%'")
    Long getRoleByFuzzyRolename(@Param("rolename")String rolename);
    @Select("select * from sys_role t where t.name like '%${rolename}%'  limit #{startPosition},#{limit}")
    List<SysRole> getRoleByFuzzyRolenameByPage(@Param("rolename")String rolename,@Param("startPosition")Integer offset, @Param("limit")Integer limit);

    int saveRole(SysRole  roleDto);

    @Select("select * from sys_role t where t.id = #{id}")
    SysRole getById(Integer id);

    int update(RoleDto roleDto);
    @Delete("delete from sys_role where id = #{id}")
    void delete(Integer id);
}
