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

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    SysUser getUserByPage(String phone);

    /**
     * 根据用户id来查询用户
     * @param id
     * @return
     */
    SysUser getUserById(Long id);

    /**
     * 更新用户表
     * @param userDto
     * @param roleId
     * @return
     */
    Results<SysUser> updateUser(UserDto userDto, Integer roleId);

    /**
     * 删除user
     * @param id
     */
    int deleteUser(Long id);

    /**
     * 通过用户名查询用户分页
     * @param offset
     * @param limit
     * @return
     */
    Results<SysUser> getUserByFuzzyUserName(String username,Integer offset, Integer limit);
}
