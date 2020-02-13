package com.ning.adminmanage.service;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysRole;
import com.ning.adminmanage.model.SysUser;

public interface RoleService {


    Results<SysRole> getAllRoles();
}
