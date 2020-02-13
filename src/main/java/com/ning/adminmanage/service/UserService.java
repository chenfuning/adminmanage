package com.ning.adminmanage.service;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dto.UserDto;
import com.ning.adminmanage.model.SysUser;

import javax.xml.transform.Result;

public interface UserService {
    /**
     * 查询用户列表。分页方式实现
     * @param offset
     * @param limit
     * @return
     */
    Results<SysUser> getAllUsersByPage(Integer offset, Integer limit);

    /**
     * 添加用户
     * @param sysUser
     * @param role
     * @return
     */
    Results save(SysUser sysUser, Integer role);
}
