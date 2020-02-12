package com.ning.adminmanage.service;

import com.ning.adminmanage.base.result.Results;
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
}
