package com.ning.adminmanage.service;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dto.RoleDto;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.model.SysUser;

public interface RoleService {


    Results<SysRole> getAllRoles();

    /**
     * 分页查询Role
     * @param offset
     * @param limit
     * @return
     */
    Results<SysRole> getAllRolesByPage(Integer offset, Integer limit);

    Results<SysRole> getRolesByFuzzyRoleName(String rolename, Integer offset, Integer limit);

    Results save(RoleDto roleDto);

    SysRole getRoleById(Integer id);

    Results update(RoleDto roleDto);


    Results<SysRole> delete(Integer id);
}
