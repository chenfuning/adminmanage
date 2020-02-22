package com.ning.adminmanage.service;

import com.alibaba.fastjson.JSONArray;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysPermission;

import javax.xml.transform.Result;

public interface PermissionService {
    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer roleId);

    Results<SysPermission> getMenuAll();

    Results save(SysPermission sysPermission);

    SysPermission getSysPermissionById(Integer id);

    Results updateSysPermission(SysPermission permission);

    Results delete(Integer id);

    /**
     * 获取动态菜单的数据
     * @param userId
     * @return
     */
    Results getMenu(Long userId);
}
