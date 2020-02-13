package com.ning.adminmanage.dao;


import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysRole;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();
}
