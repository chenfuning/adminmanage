package com.ning.adminmanage.service.impl;

import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.UserDao;
import com.ning.adminmanage.model.SysUser;
import com.ning.adminmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public Results<SysUser> getAllUsersByPage(Integer offset, Integer limit) {
        return Results.success(userDao.countAllUsers().intValue(),userDao.getAllUserByPage(offset,limit));
    }
}
