package com.ning.adminmanage.service;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.RoleUserDao;
import com.ning.adminmanage.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;

public interface RoleUserService {


    Results getSysRoleUserByUserId(Integer userId);
}
