package com.ning.adminmanage.service;

import com.alibaba.fastjson.JSONArray;
import com.ning.adminmanage.base.result.Results;
import com.ning.adminmanage.model.SysPermission;

public interface PermissionService {
    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer roleId);
}
