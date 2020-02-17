package com.ning.adminmanage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.dao.PermissionDao;
import com.ning.adminmanage.model.SysPermission;
import com.ning.adminmanage.service.PermissionService;
import com.ning.adminmanage.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public Results<JSONArray> listAllPermission() {
        log.info(getClass().getName()+".listAllPermissionn()");
        List datas=permissionDao.findAll();
        JSONArray array=new JSONArray();
        log.info(getClass().getName()+".setPermissionTree(???)");

        //构建了一个JSONarray 菜单树
        TreeUtils.setPermissionsTree(0,datas, array);
        return Results.success(array);
    }

    @Override
    public Results<SysPermission> listByRoleId(Integer roleId) {
        List<SysPermission> datas=permissionDao.listByRoleId(roleId);
        return Results.success(0,datas);
    }
}
