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
import java.util.stream.Collectors;

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

    @Override
    public Results<SysPermission> getMenuAll() {
        return Results.success(0,permissionDao.findAll());
    }

    @Override
    public Results save(SysPermission sysPermission) {
        return (permissionDao.save(sysPermission)>0)?Results.success():Results.failure();
    }

    @Override
    public SysPermission getSysPermissionById(Integer id) {
        return permissionDao.getSysPermissionById(id);
    }

    @Override
    public Results updateSysPermission(SysPermission permission) {
        return (permissionDao.update(permission) > 0) ? Results.success() : Results.failure();
    }

    @Override
    public Results delete(Integer id) {
        permissionDao.deleteById(id);
        permissionDao.deleteByParentId(id);
        return Results.success();
    }

    @Override
    public Results getMenu(Long userId) {
        List<SysPermission> datas=permissionDao.listByUserId(userId);
        //动态菜单不显示按钮，把按钮的数据即type==2的去除,留下type等于1的。
        datas=datas.stream().filter(p->p.getType().equals(1)).collect(Collectors.toList());

        JSONArray array=new JSONArray();
        log.info(getClass().getName()+".setPermissionTree(???)");

        //构建了一个JSONarray 菜单树
        TreeUtils.setPermissionsTree(0,datas, array);
        return Results.success(array);
    }
}
